package orbag.security;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AccessPolicy {
	String[] authorities();
	boolean allRequired() default false;
	AccessType[] grants() default {AccessType.USE};
}
