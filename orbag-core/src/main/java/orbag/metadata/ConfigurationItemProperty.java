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
	
	String group() default "Base Properties";
	
	boolean highlighted() default false;
}
