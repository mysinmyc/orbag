package orbag.reference;


import orbag.metadata.ConfigurationItemDescriptor;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ConfigurationItemReference reference = (ConfigurationItemReference) o;
		return Objects.equals(identifier, reference.identifier) && Objects.equals(configurationItemType, reference.configurationItemType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(identifier, configurationItemType);
	}
}
