package orbag.orchestration;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AscobMetadataProvider implements MetadataProvider {
    @Override
    public List<ConfigurationItemDescriptor> getClassesMetadata() {
        return Arrays.asList( ConfigurationItemDescriptor.fromClass(RunInfo.class,Long.class,true));
    }
}
