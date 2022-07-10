package orbag.metadata;


public class ConfigurationItemDescriptor {

	String name;
	
	Class<?> javaClass;
	
	Class<?> identifierClass;

	public ConfigurationItemDescriptor(Class<?> javaClass, String name, Class<?> identifierClass) {
		this.javaClass = javaClass;
		this.name = (name == null || name.isEmpty())? javaClass.getSimpleName() : name;
		this.identifierClass = identifierClass;
	}
	
	public Class<?> getJavaClass() {
		return this.javaClass;
	}
	
	public String getName() {
		return name;
	}
	
	public Class<?> getIdentifierClass() {
		return identifierClass;
	}

	
	public static ConfigurationItemDescriptor fromClass(Class<?> javaClass) {
		return fromClass(javaClass, Object.class);
	}
	
	public static ConfigurationItemDescriptor fromClass(Class<?> javaClass, Class<?> identifierClass) {
		
		if (!Manageable.class.isAssignableFrom(javaClass)) {
			return null;
		}
		ConfigurationItem configurationItemAnnotation = javaClass.getAnnotation(ConfigurationItem.class);
		if (configurationItemAnnotation ==null) {
			return null;
		}
		return new ConfigurationItemDescriptor(javaClass, configurationItemAnnotation.name(), identifierClass);
	}
}
