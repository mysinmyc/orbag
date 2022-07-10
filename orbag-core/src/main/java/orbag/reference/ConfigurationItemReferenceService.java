package orbag.reference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.Manageable;
import orbag.metadata.MetadataRegistry;
import orbag.utils.ConversionUtils;

@Component
public class ConfigurationItemReferenceService {

	@Autowired
	MetadataRegistry metadataRegistry;
	
	public ConfigurationItemReference getReference(ConfigurationItemDescriptor configurationItemDescriptor, Manageable<?> configurationItem) {
		return ConfigurationItemReference.fromConfigurationItem(configurationItemDescriptor, configurationItem);
	}
	
	public Object getIdentifierFromReference(ConfigurationItemReference configurationItemReference) {
		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry.getConfigurationItemDescriptorByName(configurationItemReference.configurationItemType);
		return ConversionUtils.convertString(configurationItemReference.getIdentifier(), configurationItemDescriptor.getIdentifierClass());
	}
}
