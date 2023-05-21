package orbag.samples;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Import(SamplesConfigurationClass.class)
public @interface EnableSamples {

}
