package orbag.server.metadata;

import java.util.List;

public class GetClassModelResponse {

	List<SerializableConfigurationItemDescriptor> configurationItemDescriptors;

	public List<SerializableConfigurationItemDescriptor> getConfigurationItemDescriptors() {
		return configurationItemDescriptors;
	}

	public void setConfigurationItemDescriptors(
			List<SerializableConfigurationItemDescriptor> configurationItemDescriptors) {
		this.configurationItemDescriptors = configurationItemDescriptors;
	}
}
