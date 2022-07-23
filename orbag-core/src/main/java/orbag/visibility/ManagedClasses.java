package orbag.visibility;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.Inheritance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inheritance
public @interface ManagedClasses {
	Class<?>[] value();
}
