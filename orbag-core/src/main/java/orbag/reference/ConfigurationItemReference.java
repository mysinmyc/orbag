package orbag.reference;


public class ConfigurationItemReference {

	String identifier;

	String configurationItemType;
	
	public ConfigurationItemReference() {	
	}
	
	public ConfigurationItemReference(String identifier, String configurationItemType) {
		this.identifier = identifier;
		this.configurationItemType = configurationItemType;
	}


	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getConfigurationItemType() {
		return configurationItemType;
	}

	public void setConfigurationItemType(String configurationItemType) {
		this.configurationItemType = configurationItemType;
	}

}
 