package orbag.server.metadata;


public class SerializableConfigurationItemPropertyDescriptor {
	
	String name;
	
	String displayLabel;
	
	String description;

	String category;
	boolean readOnly;
	
	boolean configurationItemReference;

	boolean collection;

	String referencedConfigurationItemType;

	public boolean isConfigurationItemReference() {
		return configurationItemReference;
	}

	public void setConfigurationItemReference(boolean configurationItemReference) {
		this.configurationItemReference = configurationItemReference;
	}

	public String getReferencedConfigurationItemType() {
		return referencedConfigurationItemType;
	}

	public void setReferencedConfigurationItemType(String referencedConfigurationItemType) {
		this.referencedConfigurationItemType = referencedConfigurationItemType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean getCollection() {
		return collection;
	}

	public void setCollection(boolean collection) {
		this.collection = collection;
	}
}
