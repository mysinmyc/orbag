package orbag.server.metadata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataRegistry;

@Component
public class MetadataService {

	@Autowired
	MetadataRegistry metadataRegistry;

	public List<SerializableConfigurationItemDescriptor> getConfigurationItemDescriptors() {
		List<SerializableConfigurationItemDescriptor> result = new ArrayList<>();
		for (ConfigurationItemDescriptor currentDescriptor : metadataRegistry.getAllConfigurationItemDescriptors()) {
			SerializableConfigurationItemDescriptor currentSerializableConfigurationItemDescriptor = new SerializableConfigurationItemDescriptor();
			currentSerializableConfigurationItemDescriptor.setName(currentDescriptor.getName());
			result.add(currentSerializableConfigurationItemDescriptor);
		}
		return result;
	}
}
