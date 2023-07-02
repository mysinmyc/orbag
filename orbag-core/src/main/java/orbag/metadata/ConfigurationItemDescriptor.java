package orbag.metadata;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import orbag.util.MyReflectionUtils;
import orbag.util.UnsafeConsumer;

public class ConfigurationItemDescriptor {

	String name;

	Class<?> javaClass;

	Class<?> identifierClass;

	Map<String, ConfigurationItemPropertyDescriptor> properties;

	String category;

	String displayLabel;

	boolean allowCreation;

	boolean readOnly;

	boolean internal;

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

	public <T extends Throwable> void forEachProperty(
			UnsafeConsumer<ConfigurationItemPropertyDescriptor, T> propertyConsumer) throws T {
		for (ConfigurationItemPropertyDescriptor propertyDescriptor : properties.values()) {
			propertyConsumer.accept(propertyDescriptor);
		}
	}

	public boolean isAllowCreation() {
		return allowCreation;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public boolean isInternal() {
		return internal;
	}

	public static ConfigurationItemDescriptor fromClass(Class<?> javaClass) {
		return fromClass(javaClass, null);
	}

	public static ConfigurationItemDescriptor fromClass(Class<?> javaClass,Class<?> identifierClass) {
		return fromClass(javaClass, identifierClass,true);
	}


	public static ConfigurationItemDescriptor fromClass(Class<?> javaClass, Class<?> identifierClass,boolean buildProperties) {
		ConfigurationItem configurationItemAnnotation = javaClass.getAnnotation(ConfigurationItem.class);
		if (configurationItemAnnotation == null || configurationItemAnnotation.hidden()) {
			return null;
		}
		ConfigurationItemDescriptor configurationItemDescriptor = new ConfigurationItemDescriptor(javaClass,
				configurationItemAnnotation.name(),
				identifierClass == null ? configurationItemAnnotation.identifierClass() : identifierClass);
		configurationItemDescriptor.category = configurationItemAnnotation.category();
		configurationItemDescriptor.displayLabel = configurationItemAnnotation.displayLabel().isEmpty()
				? DisplayLabelUtils.camelToSpaced( configurationItemDescriptor.getName())
				: configurationItemAnnotation.displayLabel();
		configurationItemDescriptor.allowCreation = configurationItemAnnotation.allowCreation();
		configurationItemDescriptor.readOnly = configurationItemAnnotation.readOnly();
		configurationItemDescriptor.internal = configurationItemAnnotation.internal();
		if (buildProperties) {
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
						propertyDescriptor.setCollection(Collection.class.isAssignableFrom(m.getReturnType()));
						ConfigurationItem annotation = m.getReturnType().getAnnotation(ConfigurationItem.class);
						if (annotation != null) {
							propertyDescriptor
									.setReferencedConfigurationItemType(ConfigurationItemDescriptor.fromClass(m.getReturnType(), null, false));
						}
					}

					ConfigurationItemProperty propertyAnnotation = m.getAnnotation(ConfigurationItemProperty.class);
					if (propertyAnnotation != null) {
						propertyDescriptor.setVisible(!propertyAnnotation.hidden());
						propertyDescriptor.setDisplayLabel(
								propertyAnnotation.displayLabel().isEmpty() ? DisplayLabelUtils.camelToSpaced(name) : propertyAnnotation.displayLabel());
						propertyDescriptor.setDescription(propertyAnnotation.description());
						propertyDescriptor.setCategory(propertyAnnotation.category());
						propertyDescriptor.setHighlighted(propertyAnnotation.highlighted());
						propertyDescriptor.setMandatoryForCreation(propertyAnnotation.mandatoryForCreation());
						propertyDescriptor
								.setReadOnly(configurationItemAnnotation.readOnly() || propertyAnnotation.readOnly());
						if (propertyDescriptor.isCollection()) {
							ConfigurationItem itemClassConfigurationItemAnnotation = propertyAnnotation.itemsClass().getAnnotation(ConfigurationItem.class);
							if (itemClassConfigurationItemAnnotation != null) {
								propertyDescriptor
										.setReferencedConfigurationItemType(ConfigurationItemDescriptor.fromClass(propertyAnnotation.itemsClass(), null, false));
							}
						}
					}
				});
			});
			Map<String, ConfigurationItemPropertyDescriptor> visibleProperties = new HashMap<String, ConfigurationItemPropertyDescriptor>();
			properties.forEach((name, property) -> {
				property.setReadOnly(property.isReadOnly() || property.getSetterMethod() == null);
				if (property.isVisible() && property.getGetterMethod() != null) {
					visibleProperties.put(name, property);
				}
			});
			configurationItemDescriptor.properties = visibleProperties;
		}
		return configurationItemDescriptor;
	}

}
