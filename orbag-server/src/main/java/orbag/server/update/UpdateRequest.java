package orbag.server.update;

import orbag.input.SerializableFieldGroup;
import orbag.reference.ConfigurationItemReference;

public class UpdateRequest {

	ConfigurationItemReference configurationItem;
	
	SerializableFieldGroup properties;

	public ConfigurationItemReference getConfigurationItem() {
		return configurationItem;
	}

	public void setConfigurationItem(ConfigurationItemReference configurationItem) {
		this.configurationItem = configurationItem;
	}

	public SerializableFieldGroup getProperties() {
		return properties;
	}

	public void setProperties(SerializableFieldGroup properties) {
		this.properties = properties;
	}
}
