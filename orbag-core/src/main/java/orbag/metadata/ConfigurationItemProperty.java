package orbag.metadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ConfigurationItemProperty {
	
	String displayLabel() default "";
	
	String description() default "";

	String category() default "Base Properties";

	boolean highlighted() default false;
	
	boolean mandatoryForCreation() default false;
	
	boolean hidden() default false;
	
	boolean readOnly() default false;

	Class<?> itemsClass() default Object.class;
}
