package orbag.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MyReflectionUtils {
	
	public static void forEachDecladerField(Class<?> sourceClass, Consumer<Field> fieldConsumer) {		
		for (Field currentField :  sourceClass.getDeclaredFields()) {
			fieldConsumer.accept(currentField);
		}
		Class<?> superClass = sourceClass.getSuperclass();
		if (superClass!=null && superClass != Object.class) {
			forEachDecladerField(superClass, fieldConsumer);
		}
	}
	
	public static void forEachDeclaredMethod(Class<?> sourceClass, Consumer<Method> methodConsumer) {		
		for (Method currentMethod :  sourceClass.getDeclaredMethods()) {
			methodConsumer.accept(currentMethod);
		}
		Class<?> superClass = sourceClass.getSuperclass();
		if (superClass!=null && superClass != Object.class) {
			forEachDeclaredMethod(superClass, methodConsumer);
		}
	}
		
	public static void extractJavaBeanPropertyFromMethodInto(Method method, BiConsumer<String,Boolean> consumer) {
		String propertyName = method.getName();
		if (method.getParameterCount()==0 && propertyName.length() > 3 && propertyName.startsWith("get")) {
			consumer.accept(propertyName.substring(3, 4).toLowerCase() +propertyName.substring(4), false);
			return;
		} 
		if (method.getParameterCount()==0 && propertyName.length() > 2 && propertyName.startsWith("is")) {
			consumer.accept(propertyName.substring(2, 3).toLowerCase() +propertyName.substring(3), false); 
			return;
		}
		if (method.getParameterCount()==1 && propertyName.length() > 3 && propertyName.startsWith("set")) {
			consumer.accept(propertyName.substring(3, 4).toLowerCase() +propertyName.substring(4), true);
			return;
		}
	}
		
}
