package orbag.metadata;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import orbag.util.MyReflectionUtils;

public class ConfigurationItemDescriptor {

	String name;

	Class<?> javaClass;

	Class<?> identifierClass;

	Map<String, ConfigurationItemPropertyDescriptor> properties;

	String category;

	String displayLabel;
	
	boolean allowCreation;

	protected ConfigurationItemDescriptor(Class<?> javaClass, String name, Class<?> identifierClass) {
		this.javaClass = javaClass;
		this.name = (name == null || name.isEmpty()) ? javaClass.getSimpleName() : name;
		this.identifierClass = identifierClass;
	}

	public Class<?> getJavaClass() {
		return this.javaClass;
	}

	public String getName() {
		return name;
	}

	public String getDisplayLabel() {
		return displayLabel;
	}

	public String getCategory() {
		return category;
	}

	public Class<?> getIdentifierClass() {
		return identifierClass;
	}

	public ConfigurationItemPropertyDescriptor getProperty(String name) {
		return properties.get(name);
	}

	public Collection<ConfigurationItemPropertyDescriptor> getProperties() {
		return properties.values();
	}

	public void forEachProperty(Consumer<ConfigurationItemPropertyDescriptor> propertyConsumer) {
		properties.values().forEach(propertyConsumer);
	}	

	public boolean isAllowCreation() {
		return allowCreation;
	}
	
	public static ConfigurationItemDescriptor fromClass(Class<?> javaClass) {
		return fromClass(javaClass, Object.class);
	}

	public static ConfigurationItemDescriptor fromClass(Class<?> javaClass, Class<?> identifierClass) {
		if (!Manageable.class.isAssignableFrom(javaClass)) {
			return null;
		}
		ConfigurationItem configurationItemAnnotation = javaClass.getAnnotation(ConfigurationItem.class);
		if (configurationItemAnnotation == null || configurationItemAnnotation.hidden()) {
			return null;
		}
		ConfigurationItemDescriptor configurationItemDescriptor = new ConfigurationItemDescriptor(javaClass,
				configurationItemAnnotation.name(), identifierClass);
		configurationItemDescriptor.category = configurationItemAnnotation.category();
		configurationItemDescriptor.displayLabel = configurationItemAnnotation.displayLabel().isEmpty()
				? configurationItemDescriptor.getName()
				: configurationItemAnnotation.displayLabel();
		configurationItemDescriptor.allowCreation = configurationItemAnnotation.allowCreation();
		Map<String, ConfigurationItemPropertyDescriptor> properties = new HashMap<String, ConfigurationItemPropertyDescriptor>();
		MyReflectionUtils.forEachDeclaredMethod(javaClass, m -> {
			MyReflectionUtils.extractJavaBeanPropertyFromMethodInto(m, (name, setter) -> {
				ConfigurationItemPropertyDescriptor propertyDescriptor = properties.get(name);
				if (propertyDescriptor == null) {
					propertyDescriptor = new ConfigurationItemPropertyDescriptor(configurationItemDescriptor, name);
					properties.put(name, propertyDescriptor);
				}
				if (setter) {
					propertyDescriptor.setSetterMethod(m);
				} else {
					propertyDescriptor.setGetterMethod(m);
					propertyDescriptor.setConfigurationItemReference( m.getReturnType().getAnnotation(ConfigurationItem.class)!=null)	;
				}
				ConfigurationItemProperty propertyAnnotation = m.getAnnotation(ConfigurationItemProperty.class);
				if (propertyAnnotation != null) {
					propertyDescriptor.setVisible(! propertyAnnotation.hidden());
					propertyDescriptor.setDisplayLabel(
							propertyAnnotation.displayLabel().isEmpty() ? name : propertyAnnotation.displayLabel());
					propertyDescriptor.setDescription(propertyAnnotation.description());
					propertyDescriptor.setHighlighted(propertyAnnotation.highlighted());
					propertyDescriptor.setMandatoryForCreation(propertyAnnotation.mandatoryForCreation());
				}
			});
		});
		Map<String, ConfigurationItemPropertyDescriptor> visibleProperties = new HashMap<String, ConfigurationItemPropertyDescriptor>();
		properties.forEach((name, property) -> {
			if (property.isVisible() && property.getGetterMethod() != null) {
				visibleProperties.put(name, property);
			}
		});
		configurationItemDescriptor.properties = visibleProperties;
		return configurationItemDescriptor;
	}
}
