package orbag.server.metadata;

import java.util.ArrayList;
import java.util.List;

import orbag.metadata.UnmanagedObjectException;
import orbag.security.AccessType;
import orbag.security.Grants;
import orbag.security.OrbagSecurityException;
import orbag.security.SecurityAssertionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.ConfigurationItemPropertyDescriptor;
import orbag.metadata.MetadataRegistry;

@Component
public class MetadataService {

	@Autowired
	MetadataRegistry metadataRegistry;

	@Autowired
	SecurityAssertionService securityAssertionService;

	protected SerializableConfigurationItemPropertyDescriptor serializeProperty(
			ConfigurationItemPropertyDescriptor property, Authentication user) {
		SerializableConfigurationItemPropertyDescriptor result = new SerializableConfigurationItemPropertyDescriptor();
		result.setName(property.getName());
		result.setDisplayLabel(property.getDisplayLabel());
		result.setDescription(property.getDescription());
		result.setCategory(property.getCategory());
		result.setReadOnly(property.isReadOnly());
		result.setCollection(property.isCollection());
		result.setConfigurationItemReference(property.isConfigurationItemReference());
		result.setReferencedConfigurationItemType(property.getReferencedConfigurationItemType()==null?null: property.getReferencedConfigurationItemType().getName());
		return result;
	}

	public List<SerializableConfigurationItemDescriptor> getConfigurationItemDescriptors(boolean includeProperties, Authentication user) {
		List<SerializableConfigurationItemDescriptor> result = new ArrayList<>();
		for (ConfigurationItemDescriptor currentDescriptor : metadataRegistry.getAllConfigurationItemDescriptors()) {
			if (securityAssertionService.hasAuthorizationToConfigurationItemDescriptor(currentDescriptor, user,AccessType.values()) &&  ! currentDescriptor.isInternal()) {
				try {
					result.add(serialize(currentDescriptor, includeProperties,user));
				} catch (OrbagSecurityException e) {
					throw new RuntimeException("Unexpected security exception",e);
				}
			}
		}
		return result;
	}

	public SerializableConfigurationItemDescriptor getSerializableConfigurationItemDescriptor(
			String configurationItem, boolean includeProperties, Authentication user) throws UnmanagedObjectException, OrbagSecurityException {
		return serialize(metadataRegistry.getConfigurationItemDescriptorByName(configurationItem),
				includeProperties, user);
	}
	
	public SerializableConfigurationItemDescriptor getSerializableConfigurationItemDescriptor(
			Class<?> configurationItemClass, boolean includeProperties, Authentication user) throws UnmanagedObjectException, OrbagSecurityException {
		return serialize(metadataRegistry.getConfigurationItemDescriptorByClass(configurationItemClass),
				includeProperties,user);
	}

	protected SerializableConfigurationItemDescriptor serialize(ConfigurationItemDescriptor configurationItemDescriptor,
			boolean includeProperties, Authentication user) throws OrbagSecurityException {
		securityAssertionService.assertAuthorizationToConfigurationItemDescriptor(configurationItemDescriptor,user, AccessType.values());
		Grants configurationItemGrants = securityAssertionService.getAccessRightsToConfigurationItemDescriptor(configurationItemDescriptor,user);
		SerializableConfigurationItemDescriptor result = new SerializableConfigurationItemDescriptor();
		result.setName(configurationItemDescriptor.getName());
		result.setCategory(configurationItemDescriptor.getCategory());
		result.setDisplayLabel(configurationItemDescriptor.getDisplayLabel());
		result.setAllowCreation(configurationItemDescriptor.isAllowCreation() && configurationItemGrants.hasAnyAccess(AccessType.CREATE));
		result.setReadOnly(configurationItemDescriptor.isReadOnly() || (! configurationItemGrants.hasAnyAccess(AccessType.MODIFY)));
		if (includeProperties) {
			List<SerializableConfigurationItemPropertyDescriptor> properties = new ArrayList<SerializableConfigurationItemPropertyDescriptor>();
			configurationItemDescriptor.forEachProperty(p -> {
				if (securityAssertionService.hasAuthorizationToConfigurationItemPropertyDescriptor(p,user,AccessType.values())) properties.add(serializeProperty(p,user));
			});
			result.setProperties(properties);
		}
		return result;
	}
}
