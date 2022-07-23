package orbag.server.edit;

import java.util.Map;

import orbag.reference.ConfigurationItemReference;
import orbag.server.metadata.SerializableConfigurationItemDescriptor;

public class GetConfigurationItemResponse {

	
	ConfigurationItemReference reference;
	
	SerializableConfigurationItemDescriptor metadata;
	
	Map<String,Object> properties;

	public ConfigurationItemReference getReference() {
		return reference;
	}

	public void setReference(ConfigurationItemReference reference) {
		this.reference = reference;
	}

	public SerializableConfigurationItemDescriptor getMetadata() {
		return metadata;
	}

	public void setMetadata(SerializableConfigurationItemDescriptor metadata) {
		this.metadata = metadata;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
}
