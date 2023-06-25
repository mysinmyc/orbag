package orbag.input;

import orbag.reference.ConfigurationItemReference;

import java.util.List;

public class ConfigurationItemReferenceListField extends InputFieldBase<List<ConfigurationItemReference>> {

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
