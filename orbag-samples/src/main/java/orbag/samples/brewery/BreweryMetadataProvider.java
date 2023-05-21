package orbag.samples.brewery;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataProvider;

@Component
public class BreweryMetadataProvider implements MetadataProvider {

	@Override
	public List<ConfigurationItemDescriptor> getClassesMetadata() {
		return Arrays.asList( ConfigurationItemDescriptor.fromClass(Brewery.class,String.class,true));
	}

}
