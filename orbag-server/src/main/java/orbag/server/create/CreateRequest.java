package orbag.server.create;

import orbag.input.SerializableFieldGroup;

public class CreateRequest {

	String configurationItemType;
	
	SerializableFieldGroup parameters;

	public String getConfigurationItemType() {
		return configurationItemType;
	}

	public void setConfigurationItemType(String configurationItemType) {
		this.configurationItemType = configurationItemType;
	}

	public SerializableFieldGroup getParameters() {
		return parameters;
	}

	public void setParameters(SerializableFieldGroup parameters) {
		this.parameters = parameters;
	}
}
