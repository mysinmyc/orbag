package orbag.input;

import orbag.reference.ConfigurationItemReferenceExt;

public class ConfigurationItemReferenceField extends InputFieldBase<ConfigurationItemReferenceExt> {

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
