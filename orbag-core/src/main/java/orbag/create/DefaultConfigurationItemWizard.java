package orbag.create;

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

@ManagedClasses(Object.class)
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultConfigurationItemWizard implements ConfigurationItemWizard{

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
	public Object create(FieldGroupConsumer parameters, CreationContext context) {
		try {		
			Object newObject = context.getConfigurationItemDescriptor().getJavaClass().getDeclaredConstructor().newInstance();
			context.getConfigurationItemDescriptor().forEachProperty( p -> {
				if ( p.isMandatoryForCreation()) {
					InputFieldBase<?> parameter = parameters.getField(p.getName());
					
					if (parameter.isEmpty()) {
						throw new RuntimeException("Missing value for "+parameter.getDisplayLabel());
					}
					p.getSetterMethod().invoke(newObject, fieldManagementUtils.fieldToValue(parameter, p.getValueType()));
				}
			});
			return dao.create(newObject);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		
	}

}
