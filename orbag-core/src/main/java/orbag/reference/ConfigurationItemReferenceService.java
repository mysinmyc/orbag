package orbag.reference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.Manageable;
import orbag.metadata.MetadataRegistry;
import orbag.util.ConversionUtils;

@Component
public class ConfigurationItemReferenceService {

	@Autowired
	MetadataRegistry metadataRegistry;
	
	public ConfigurationItemReferenceExt getReference(Object object) {
		if (! (object instanceof Manageable<?>)) {
			return null;
		}
		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry.getConfigurationItemDescriptorByClass(object.getClass());
		if (configurationItemDescriptor==null) {
			return null;
		}
		return ConfigurationItemReferenceExt.fromConfigurationItem(configurationItemDescriptor, (Manageable<?>) object);
	}

	public Object getIdentifierFromReference(ConfigurationItemReference configurationItemReference) {
		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry.getConfigurationItemDescriptorByName(configurationItemReference.configurationItemType);
		return ConversionUtils.convertString(configurationItemReference.getIdentifier(), configurationItemDescriptor.getIdentifierClass());
	}

}
