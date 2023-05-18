package orbag.orchestration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(OrchestrationConfigurationClass.class)
public @interface EnableOrchestration {
}
