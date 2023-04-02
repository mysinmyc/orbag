package orbag.server.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.data.DataUtils;
import orbag.input.FieldManagementUtils;
import orbag.input.InputFieldBase;
import orbag.input.SerializableFieldGroup;
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.ConfigurationItemPropertyDescriptor;
import orbag.metadata.MetadataRegistry;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.security.AccessType;
import orbag.security.OrbagSecurityException;
import orbag.security.SecurityAssertionService;
import orbag.server.OrbagServerException;

@Component
public class UpdateService {

	@Autowired
	ConfigurationItemReferenceService referenceService;

	@Autowired
	MetadataRegistry metadataRegistry;

	@Autowired
	ConfigurationItemDao dao;

	@Autowired
	SecurityAssertionService securityAssertionService;

	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;

	@Autowired
	FieldManagementUtils fieldManagementUtils;

	public UpdateRequest getUpdateRequestTemplateFor(ConfigurationItemReference configurationItemReference,
			Authentication user) throws OrbagSecurityException {
		Object ci = dao.getCi(configurationItemReference);
		if (ci == null) {
			throw new OrbagServerException("Object not found");
		}
		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry
				.getConfigurationItemDescriptorByClass(ci.getClass());
		if (configurationItemDescriptor == null) {
			throw new OrbagServerException("No access to properties");
		}
		securityAssertionService.assertAuthorizationToConfigurationItemDescriptor(configurationItemDescriptor, user,
				AccessType.READ, AccessType.MODIFY);

		UpdateRequest updateRequest = new UpdateRequest();
		SerializableFieldGroup fieldGroup = new SerializableFieldGroup();
		configurationItemDescriptor.getProperties().forEach(p -> {
			if (securityAssertionService.hasAuthorizationToConfigurationItemPropertyDescriptor(p, user, AccessType.READ,
					AccessType.MODIFY)) {
				addProperty(ci, p, fieldGroup);
			}
		});
		updateRequest.setProperties(fieldGroup);
		updateRequest.setConfigurationItem(configurationItemReferenceService.getReference(ci));
		return updateRequest;
	}

	@SuppressWarnings("unchecked")
	private <T> void addProperty(Object ci, ConfigurationItemPropertyDescriptor p, SerializableFieldGroup fieldGroup) {
		InputFieldBase<T> field = (InputFieldBase<T>) DataUtils.buildInputFieldFromConfigurationItemProperty(p,
				fieldGroup);
		if (field != null) {
			field.setReadOnly(p.isReadOnly());
			try {
				Object value = p.getGetterMethod().invoke(ci);
				fieldManagementUtils.setFieldValue(value, field);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected ConfigurationItemReference update(UpdateRequest request, Authentication user)
			throws OrbagSecurityException {
		Object ci = dao.getCi(request.getConfigurationItem());
		if (ci == null) {
			throw new OrbagServerException("Object not found");
		}
		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry
				.getConfigurationItemDescriptorByClass(ci.getClass());
		if (configurationItemDescriptor == null) {
			throw new OrbagServerException("No access to properties");
		}
		securityAssertionService.assertAuthorizationToConfigurationItemDescriptor(configurationItemDescriptor, user,
				AccessType.MODIFY);
		for (InputFieldBase<?> parameter : request.getProperties().getFields()) {
			if (!parameter.isChanged()) {
				continue;
			}
			ConfigurationItemPropertyDescriptor property = configurationItemDescriptor.getProperty(parameter.getName());
			if (property == null) {
				throw new RuntimeException("Invalid property " + parameter.getName());
			}
			if (property.isReadOnly()) {
				throw new RuntimeException("ReadOnly property " + parameter.getName());
			}
			try {
				ConfigurationItemPropertyDescriptor propertyDescriptor = configurationItemDescriptor
						.getProperty(parameter.getName());
				securityAssertionService.assertAuthorizationToConfigurationItemPropertyDescriptor(propertyDescriptor,
						user, AccessType.MODIFY);
				propertyDescriptor.getSetterMethod().invoke(ci,
						fieldManagementUtils.fieldToValue(parameter, propertyDescriptor.getValueType()));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		dao.update(ci);
		return configurationItemReferenceService.getReference(ci);
	}

}
