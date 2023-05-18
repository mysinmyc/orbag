package orbag.reference;

import orbag.dao.ConfigurationItemDao;
import orbag.metadata.DisplayLabelUtils;
import orbag.metadata.UnmanagedObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataRegistry;
import orbag.util.ConversionUtils;

@Component
public class ConfigurationItemReferenceService {

	@Autowired
	@Lazy
	ConfigurationItemDao dao;

	@Autowired
	MetadataRegistry metadataRegistry;


	public ConfigurationItemReference getReference(Object object) throws UnmanagedObjectException {
		if (object instanceof ConfigurationItemReference) {
			return (ConfigurationItemReference) object;
		}
		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry.getConfigurationItemDescriptorByClass(object.getClass());
		return ConfigurationItemReference.fromConfigurationItem(configurationItemDescriptor,dao.getIdentifier(object), DisplayLabelUtils.getDisplayLabel(object));
	}

	public Object getIdentifierFromReference(ConfigurationItemReference configurationItemReference) throws UnmanagedObjectException {
		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry.getConfigurationItemDescriptorByName(configurationItemReference.getConfigurationItemType());
		return ConversionUtils.convertString(configurationItemReference.getIdentifier(), configurationItemDescriptor.getIdentifierClass());
	}


}
