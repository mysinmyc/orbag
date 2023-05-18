package orbag.reference;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import orbag.metadata.ConfigurationItemDescriptor;

@MappedSuperclass
public class ConfigurationItemReference {
	
	@Id
	String identifier;

	@Id
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
	String displayLabel;

	String configurationItemTypeDisplayLabel;
	
	public String getConfigurationItemTypeDisplayLabel() {
		return configurationItemTypeDisplayLabel;
	}

	public void setConfigurationItemTypeDisplayLabel(String configurationItemTypeDisplayLabel) {
		this.configurationItemTypeDisplayLabel = configurationItemTypeDisplayLabel;
	}

	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}
	
	public static ConfigurationItemReference fromConfigurationItem(ConfigurationItemDescriptor configurationItemDescriptor, Object identifier, String displayLabel) {
		ConfigurationItemReference result = new ConfigurationItemReference(identifier.toString(), configurationItemDescriptor.getName());
		result.setDisplayLabel(displayLabel);
		result.setConfigurationItemTypeDisplayLabel(configurationItemDescriptor.getDisplayLabel());
		return result;
	}

}
