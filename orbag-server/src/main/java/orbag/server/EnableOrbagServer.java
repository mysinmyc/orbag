package orbag.server;

import orbag.OrbagCoreConfigurationClass;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(OrbagServerConfigurationClass.class)
public @interface EnableOrbagServer {
}
