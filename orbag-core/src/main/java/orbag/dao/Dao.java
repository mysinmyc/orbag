package orbag.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataRegistry;
import orbag.reference.ConfigurationItemReference;

@Component
public class Dao {

	@Autowired
	List<OrbagRepository> repositories;
	
	@Autowired
	MetadataRegistry metadataRegistry;
	
	OrbagRepository getRepositoryFor(Class<?> javaClass) {
		return repositories.stream().filter(r -> r.isManaged(javaClass)).findFirst().orElse(null);
	}
	
	public <T> List<T> list(Class<T> javaClass) {
		return getRepositoryFor(javaClass).list(javaClass);
	}
	
	public Object getCi(ConfigurationItemReference reference) {
		ConfigurationItemDescriptor descriptor = metadataRegistry.getConfigurationItemDescriptorByName(reference.getConfigurationItemType());
		if (descriptor==null) {
			return null;
		}
		OrbagRepository  repository = getRepositoryFor(descriptor.getJavaClass());
		return repository.getById(reference.getIdentifier(), descriptor.getJavaClass());
	}
	
	
	public List<?> getCis(List<ConfigurationItemReference> configurationItemReferences) {
		return configurationItemReferences.stream().map(this::getCi).filter(i -> i !=null).toList();
	}
	
	
	public void delete(Object object) {
		getRepositoryFor(object.getClass()).delete(object);
	}
}
