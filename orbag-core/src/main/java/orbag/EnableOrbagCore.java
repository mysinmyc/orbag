package orbag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Import(OrbagCoreConfigurationClass.class)
public @interface EnableOrbagCore {

}
