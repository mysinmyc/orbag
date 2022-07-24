package orbag.metadata;

import java.lang.reflect.Method;

public class ConfigurationItemPropertyDescriptor {

	String name;
	
	ConfigurationItemDescriptor configurationItem;
	
	boolean configurationItemReference;

	boolean visible;
	
	boolean highlighted;

	boolean mandatoryForCreation;
	
	String displayLabel;
	
	String description;

	Method getterMethod;
	
	Method setterMethod;
	
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
		return configurationItemReference;
	}

	protected void setConfigurationItemReference(boolean configurationItemReference) {
		this.configurationItemReference = configurationItemReference;
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
}
