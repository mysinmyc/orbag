package orbag.metadata;

import java.util.Arrays;
import java.util.List;

public class StaticMetadataProvider implements MetadataProvider {

	List<Class<?>> javaClasses;
	
	public StaticMetadataProvider(Class<?>... javaClasses) {
		this.javaClasses = Arrays.asList(javaClasses);
	}
	
	@Override
	public List<ConfigurationItemDescriptor> getClassesMetadata() {
		return javaClasses.stream().map(ConfigurationItemDescriptor::fromClass).filter(c -> c!=null).toList();
	}

}
