package orbag.input;

import orbag.reference.ConfigurationItemReference;

public class ConfigurationItemReferenceField extends InputFieldBase<ConfigurationItemReference> {

	@Override
	public void parseValue(String value) {
		
	}

	String configurationItemType;

	public String getConfigurationItemType() {
		return configurationItemType;
	}

	public void setConfigurationItemType(String configurationItemType) {
		this.configurationItemType = configurationItemType;
	}
	

}
