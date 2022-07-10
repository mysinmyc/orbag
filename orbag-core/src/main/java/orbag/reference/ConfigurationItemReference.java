package orbag.reference;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.Manageable;

public class ConfigurationItemReference {

	
	String identifier;

	String configurationItemType;
	
	String displayLabel;

	public ConfigurationItemReference() {	
	}
	
	public ConfigurationItemReference(String identifier, String configurationItemType, String displayLabel) {
		this.identifier = identifier;
		this.configurationItemType = configurationItemType;
		this.displayLabel = displayLabel;
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

	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}
	
	public static ConfigurationItemReference fromConfigurationItem(ConfigurationItemDescriptor configurationItemDescriptor, Manageable<?> configurationItem) {
		return new ConfigurationItemReference(configurationItem.getIdentifier().toString(), configurationItemDescriptor.getName(), configurationItem.getDisplayLabel());
	}
}
 