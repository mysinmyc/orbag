package orbag.visibility;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface ManagedClasses {
	Class<?>[] value();
}
