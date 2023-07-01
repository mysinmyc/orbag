package orbag.metadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
public @interface ConfigurationItem {
	
	String category() default "Uncategorized";
	
	String name() default "";
	
	String displayLabel() default "";
	
	boolean allowCreation() default true;
	
	boolean hidden() default false;

	boolean internal() default false;

	boolean readOnly() default false;

	Class<?> identifierClass() default Object.class;
	
}
