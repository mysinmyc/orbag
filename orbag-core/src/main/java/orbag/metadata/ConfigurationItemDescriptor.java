package orbag.metadata;


public class ConfigurationItemDescriptor {

	String name;
	
	Class<?> javaClass;
	
	public ConfigurationItemDescriptor(Class<?> javaClass, String name) {
		this.javaClass = javaClass;
		this.name = (name == null || name.isEmpty())? javaClass.getSimpleName() : name;
	}
	
	public Class<?> getJavaClass() {
		return this.javaClass;
	}
	
	public String getName() {
		return name;
	}
	
	public static ConfigurationItemDescriptor fromClass(Class<?> javaClass) {
		
		if (!Manageable.class.isAssignableFrom(javaClass)) {
			return null;
		}
		ConfigurationItem configurationItemAnnotation = javaClass.getAnnotation(ConfigurationItem.class);
		if (configurationItemAnnotation ==null) {
			return null;
		}
		return new ConfigurationItemDescriptor(javaClass, configurationItemAnnotation.name());
	}
}
