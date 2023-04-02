package orbag.reference;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.Manageable;

public class ConfigurationItemReferenceExt extends ConfigurationItemReference {
	
	String displayLabel;

	String configurationItemTypeDisplayLabel;
	
	public String getConfigurationItemTypeDisplayLabel() {
		return configurationItemTypeDisplayLabel;
	}

	public void setConfigurationItemTypeDisplayLabel(String configurationItemTypeDisplayLabel) {
		this.configurationItemTypeDisplayLabel = configurationItemTypeDisplayLabel;
	}

	public ConfigurationItemReferenceExt() {
		super();
	}
	
	public ConfigurationItemReferenceExt(String identifier, String configurationItemType) {
		super(identifier,configurationItemType);
	}

	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}
	
	public static ConfigurationItemReferenceExt fromConfigurationItemRefence(ConfigurationItemReference reference) {
		ConfigurationItemReferenceExt result = new ConfigurationItemReferenceExt(reference.getIdentifier(), reference.getConfigurationItemType());
		return result;
	}
	
	public static ConfigurationItemReferenceExt fromConfigurationItem(ConfigurationItemDescriptor configurationItemDescriptor, Manageable<?> configurationItem) {
		ConfigurationItemReferenceExt result = new ConfigurationItemReferenceExt(configurationItem.getIdentifier().toString(), configurationItemDescriptor.getName());
		result.setDisplayLabel(configurationItem.getDisplayLabel());
		result.setConfigurationItemTypeDisplayLabel(configurationItemDescriptor.getDisplayLabel());
		return result;
	}
}
 