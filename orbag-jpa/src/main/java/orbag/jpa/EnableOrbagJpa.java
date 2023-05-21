package orbag.jpa;

import orbag.OrbagCoreConfigurationClass;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(OrbagJpaConfigurationClass.class)
public @interface EnableOrbagJpa {
}
