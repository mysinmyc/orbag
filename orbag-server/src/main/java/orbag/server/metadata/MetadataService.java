package orbag.server.metadata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.ConfigurationItemPropertyDescriptor;
import orbag.metadata.MetadataRegistry;

@Component
public class MetadataService {

	@Autowired
	MetadataRegistry metadataRegistry;

	protected SerializableConfigurationItemPropertyDescriptor serializeProperty(
			ConfigurationItemPropertyDescriptor property) {
		SerializableConfigurationItemPropertyDescriptor result = new SerializableConfigurationItemPropertyDescriptor();
		result.setName(property.getName());
		result.setDisplayLabel(property.getDisplayLabel());
		result.setDescription(property.getDescription());
		result.setReadOnly(property.isReadOnly());
		return result;
	}

	public List<SerializableConfigurationItemDescriptor> getConfigurationItemDescriptors(boolean includeProperties) {
		List<SerializableConfigurationItemDescriptor> result = new ArrayList<>();
		for (ConfigurationItemDescriptor currentDescriptor : metadataRegistry.getAllConfigurationItemDescriptors()) {
			result.add(serialize(currentDescriptor, includeProperties));
		}
		return result;
	}

	public SerializableConfigurationItemDescriptor getSerializableConfigurationItemDescriptor(
			String configurationItem, boolean includeProperties) {
		return serialize(metadataRegistry.getConfigurationItemDescriptorByName(configurationItem),
				includeProperties);
	}
	
	public SerializableConfigurationItemDescriptor getSerializableConfigurationItemDescriptor(
			Class<?> configurationItemClass, boolean includeProperties) {
		return serialize(metadataRegistry.getConfigurationItemDescriptorByClass(configurationItemClass),
				includeProperties);
	}

	protected SerializableConfigurationItemDescriptor serialize(ConfigurationItemDescriptor configurationItemDescriptor,
			boolean includeProperties) {
		SerializableConfigurationItemDescriptor result = new SerializableConfigurationItemDescriptor();
		result.setName(configurationItemDescriptor.getName());
		result.setCategory(configurationItemDescriptor.getCategory());
		result.setDisplayLabel(configurationItemDescriptor.getDisplayLabel());
		result.setAllowCreation(configurationItemDescriptor.isAllowCreation());
		result.setReadOnly(configurationItemDescriptor.isReadOnly());
		if (includeProperties) {
			List<SerializableConfigurationItemPropertyDescriptor> properties = new ArrayList<SerializableConfigurationItemPropertyDescriptor>();
			configurationItemDescriptor.forEachProperty(p -> properties.add(serializeProperty(p)));
			result.setProperties(properties);
		}
		return result;
	}
}
