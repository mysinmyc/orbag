package orbag.util.placeholder;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaceholderMetadataProvider  implements MetadataProvider {


    @Override
    public List<ConfigurationItemDescriptor> getClassesMetadata() {
        return List.of(ConfigurationItemDescriptor.fromClass(PlaceholderConfigurationItem.class,String.class));
    }
}
