package orbag.security;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AccessRestricted {
	AccessPolicy[] value() default {}; 
	Class<?>[] inherts() default {};
}
