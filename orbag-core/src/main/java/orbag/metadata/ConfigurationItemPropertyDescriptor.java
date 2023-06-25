package orbag.metadata;

import java.lang.reflect.Method;

public class ConfigurationItemPropertyDescriptor {

	String name;
	
	ConfigurationItemDescriptor configurationItem;
	
	ConfigurationItemDescriptor referencedConfigurationItemType;
	

	boolean visible;
	
	boolean highlighted;

	boolean mandatoryForCreation;

	boolean collection;

	String displayLabel;
	
	String description;

	String category;

	Method getterMethod;
	
	Method setterMethod;
	
	boolean readOnly;

	protected ConfigurationItemPropertyDescriptor(ConfigurationItemDescriptor configurationItem, String name) {
		this.configurationItem = configurationItem;
		this.name=name;
	}

	public ConfigurationItemDescriptor getConfigurationItem() {
		return configurationItem;
	}


	public String getName() {
		return name;
	}	
	public Boolean isVisible() {
		return visible;
	}

	protected void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getDisplayLabel() {
		return displayLabel;
	}

	protected void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	public Method getGetterMethod() {
		return getterMethod;
	}

	protected void setGetterMethod(Method getterMethod) {
		this.getterMethod = getterMethod;
	}

	public Method getSetterMethod() {
		return setterMethod;
	}

	protected void setSetterMethod(Method setterMethod) {
		this.setterMethod = setterMethod;
	}


	public boolean isConfigurationItemReference() {
		return referencedConfigurationItemType !=null;
	}
	
	public ConfigurationItemDescriptor getReferencedConfigurationItemType() {
		return referencedConfigurationItemType;
	}

	protected void setReferencedConfigurationItemType(ConfigurationItemDescriptor referencedConfigurationItemType) {
		this.referencedConfigurationItemType = referencedConfigurationItemType;
	}

	public Class<?> getValueType() {
		return getterMethod.getReturnType();
	}
		
	public boolean isHighlighted() {
		return highlighted;
	}

	protected void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public boolean isMandatoryForCreation() {
		return mandatoryForCreation;
	}
	
	protected void setMandatoryForCreation(boolean mandatoryForCreation) {
		this.mandatoryForCreation = mandatoryForCreation;
	}
		
	public boolean isReadOnly() {
		return readOnly;
	}
	
	protected void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isCollection() {
		return collection;
	}

	public void setCollection(boolean collection) {
		this.collection = collection;
	}
}
