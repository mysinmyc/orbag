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
		return result;
	}

	public List<SerializableConfigurationItemDescriptor> getConfigurationItemDescriptors() {
		List<SerializableConfigurationItemDescriptor> result = new ArrayList<>();
		for (ConfigurationItemDescriptor currentDescriptor : metadataRegistry.getAllConfigurationItemDescriptors()) {
			result.add(serialize(currentDescriptor));
		}
		return result;
	}
	
	public SerializableConfigurationItemDescriptor getSerializableConfigurationItemDescriptor(Class<?> configurationItemClass) {
		return serialize(metadataRegistry.getConfigurationItemDescriptorByClass(configurationItemClass));
	}
	
	protected SerializableConfigurationItemDescriptor serialize(ConfigurationItemDescriptor configurationItemDescriptor) {
		SerializableConfigurationItemDescriptor result = new SerializableConfigurationItemDescriptor();
		result.setName(configurationItemDescriptor.getName());
		result.setCategory(configurationItemDescriptor.getCategory());
		result.setDisplayLabel(configurationItemDescriptor.getDisplayLabel());			
		List<SerializableConfigurationItemPropertyDescriptor> properties = new ArrayList<SerializableConfigurationItemPropertyDescriptor>();
		configurationItemDescriptor.forEachProperty(p -> properties.add(serializeProperty(p)));
		result.setProperties(properties);
		return result;
	}
}
