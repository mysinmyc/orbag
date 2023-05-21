package orbag.metadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetadataRegistry {

	List<ConfigurationItemDescriptor> configurationItemDescriptors;

	public List<ConfigurationItemDescriptor> getAllConfigurationItemDescriptors() {
		return this.configurationItemDescriptors;
	}

	@Autowired(required = false)
	public void setMetadataFromProviders(List<MetadataProvider> metadataProviders) {
		List<ConfigurationItemDescriptor> newConfigurationItemDescriptors = new ArrayList<ConfigurationItemDescriptor>();
		for (MetadataProvider currentMetadataProvider : metadataProviders) {
			List<ConfigurationItemDescriptor> currentDescriptors = currentMetadataProvider.getClassesMetadata();
			if (currentDescriptors != null && !currentDescriptors.isEmpty()) {
				newConfigurationItemDescriptors.addAll(currentDescriptors);
			}
		}
		this.configurationItemDescriptors = newConfigurationItemDescriptors;
	}

	public ConfigurationItemDescriptor getConfigurationItemDescriptorByName(String configurationItemName) throws UnmanagedObjectException {
		if (configurationItemDescriptors !=null) {
			for (ConfigurationItemDescriptor currentDescriptor : configurationItemDescriptors) {
				if (currentDescriptor.getName().equals(configurationItemName)) {
					return currentDescriptor;
				}
			}
		}
		throw new UnmanagedObjectException();
	}
	
	public ConfigurationItemDescriptor getConfigurationItemDescriptorByClass(Class<?> configurationItemClass) throws UnmanagedObjectException {
		if (configurationItemDescriptors !=null) {
			for (ConfigurationItemDescriptor currentDescriptor : configurationItemDescriptors) {
				if (currentDescriptor.getJavaClass().isAssignableFrom(configurationItemClass)) {
					return currentDescriptor;
				}
			}
		}
		throw new UnmanagedObjectException();
	}

	public void setManagedClasses(Class<?>... managedClasses) {
		setMetadataFromProviders(Arrays.asList(new StaticMetadataProvider(managedClasses)));
	}
}
