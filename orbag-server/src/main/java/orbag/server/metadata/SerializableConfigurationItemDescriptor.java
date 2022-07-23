package orbag.server.metadata;

import java.util.List;

public class SerializableConfigurationItemDescriptor {
	
	String name;

	String displayLabel;
	
	String category;
	
	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	List<SerializableConfigurationItemPropertyDescriptor> properties;

	public List<SerializableConfigurationItemPropertyDescriptor> getProperties() {
		return properties;
	}

	public void setProperties(List<SerializableConfigurationItemPropertyDescriptor> properties) {
		this.properties = properties;
	}
}
