package orbag.server.update;

import orbag.dao.ConfigurationItemNotFoundException;
import orbag.metadata.UnmanagedObjectException;
import orbag.security.Grants;
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
			Authentication user) throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemNotFoundException {
		Object ci = dao.getExistingCiOrThrow(configurationItemReference);
		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry
				.getConfigurationItemDescriptorByClass(ci.getClass());
		securityAssertionService.assertAuthorizationToConfigurationItemDescriptor(configurationItemDescriptor, user,
				AccessType.READ, AccessType.MODIFY);
		UpdateRequest updateRequest = new UpdateRequest();
		SerializableFieldGroup fieldGroup = new SerializableFieldGroup();
		configurationItemDescriptor.getProperties().forEach(p -> {
			Grants propertyGrants=securityAssertionService.getAccessRightsToConfigurationItemPropertyDescriptor(p, user);
			if (propertyGrants.hasAnyAccess(AccessType.READ,AccessType.MODIFY)) {
				InputFieldBase<?> field =addProperty(ci, p, fieldGroup);
				if (! propertyGrants.hasAccess(AccessType.MODIFY)) {
					field.setReadOnly(true);
				}
			}
		});
		updateRequest.setProperties(fieldGroup);
		updateRequest.setConfigurationItem(configurationItemReferenceService.getReference(ci));
		return updateRequest;
	}

	@SuppressWarnings("unchecked")
	private <T> InputFieldBase<T> addProperty(Object ci, ConfigurationItemPropertyDescriptor p, SerializableFieldGroup fieldGroup) {
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
		return field;
	}

	protected ConfigurationItemReference update(UpdateRequest request, Authentication user)
			throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemNotFoundException {
		Object ci = dao.getExistingCiOrThrow(request.getConfigurationItem());
		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry
				.getConfigurationItemDescriptorByClass(ci.getClass());
		securityAssertionService.assertAuthorizationToConfigurationItemDescriptor(configurationItemDescriptor, user,
				AccessType.READ,AccessType.MODIFY);
		boolean changed=false;
		for (InputFieldBase<?> parameter : request.getProperties().getFields()) {
			if (!parameter.isChanged()) {
				continue;
			}
			changed=true;
			ConfigurationItemPropertyDescriptor propertyDescriptor = configurationItemDescriptor.getProperty(parameter.getName());
			if (propertyDescriptor == null) {
				throw new RuntimeException("Invalid property " + parameter.getName());
			}
			if (propertyDescriptor.isReadOnly()) {
				throw new RuntimeException("ReadOnly property " + parameter.getName());
			}
			securityAssertionService.assertAuthorizationToConfigurationItemPropertyDescriptor(propertyDescriptor,
					user, AccessType.MODIFY);
			try {
				propertyDescriptor.getSetterMethod().invoke(ci,
						fieldManagementUtils.fieldToValue(parameter, propertyDescriptor.getValueType()));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		if (changed) {
			dao.update(ci);
		}
		return configurationItemReferenceService.getReference(ci);
	}

}
