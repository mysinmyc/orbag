package orbag.create;

import orbag.metadata.ConfigurationItemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.data.DataUtils;
import orbag.input.FieldGroupBuilder;
import orbag.input.FieldGroupConsumer;
import orbag.input.FieldManagementUtils;
import orbag.input.InputFieldBase;
import orbag.visibility.ManagedClasses;

/**
 * Default implementation of configuration item wizard
 *
 * <p> It is used if there are no wizard with higher precedence
 *
 * <p> by default this wizard offer as input parameters all the properties marked as {@link ConfigurationItemProperty#mandatoryForCreation()}
 *
 * <p> {@link ConfigurationItemDao} is used to persists CIs
 */
@ManagedClasses(Object.class)
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultConfigurationItemCreationWizard implements ConfigurationItemCreationWizard {

	@Autowired
	FieldManagementUtils fieldManagementUtils;
	
	@Autowired
	ConfigurationItemDao dao;

	@Override
	public void buildCreateParameters(FieldGroupBuilder parametersBuilder, CreationContext context) {
		context.getConfigurationItemDescriptor().forEachProperty( p -> {
			if ( p.isMandatoryForCreation()) {
				DataUtils.buildInputFieldFromConfigurationItemProperty(p, parametersBuilder);
			}
		});
	}

	@Override
	public void validate(FieldGroupConsumer parameters, CreationContext context, ConfigurationItemCreationFeedback feedback) {
		context.getConfigurationItemDescriptor().forEachProperty( p -> {
			if (p.isMandatoryForCreation()) {
				InputFieldBase<?> parameter = parameters.getField(p.getName());
				if (parameter.isEmpty()) {
					feedback.addValidationError("Missing " + parameter.getDisplayLabel(), p.getName());
				}
			}
		});
	}

	@Override
	public Object create(FieldGroupConsumer parameters, CreationContext context) throws ConfigurationItemCreationException {
		try {		
			Object newObject = context.getConfigurationItemDescriptor().getJavaClass().getDeclaredConstructor().newInstance();
			context.getConfigurationItemDescriptor().forEachProperty( p -> {
				if ( p.isMandatoryForCreation()) {
					InputFieldBase<?> parameter = parameters.getField(p.getName());
					p.getSetterMethod().invoke(newObject, fieldManagementUtils.fieldToValue(parameter, p.getValueType()));
				}
			});
			return dao.create(newObject);
		} catch (Exception e) {
			throw new ConfigurationItemCreationException(e.getMessage(),e);
		}
	}

}
