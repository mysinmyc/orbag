package orbag.dao;

import java.util.List;

import javax.naming.LimitExceededException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.data.PaginationInfo;
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataRegistry;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.search.SearchConditions;
import orbag.util.UnsafeConsumer;

@Component
public class ConfigurationItemDao {

	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;

	@Autowired(required = false)
	List<OrbagRepository> repositories;

	@Autowired
	MetadataRegistry metadataRegistry;

	public OrbagRepository getRepositoryFor(Class<?> javaClass) {
		PersistedBy annotation = javaClass.getAnnotation(PersistedBy.class);
		if (annotation != null) {
			return repositories.stream().filter(r -> r.getClass() == annotation.value()).findFirst()
					.orElseThrow(() -> new RuntimeException(
							"Invalid repository in PersistedBy annotation of " + javaClass.getName()));
		}
		return repositories.stream().filter(r -> r.isManaged(javaClass)).findFirst()
				.orElseThrow(() -> new RuntimeException("No repostitory avaialble for " + javaClass.getName()));
	}

	public <T> void listInto(Class<T> javaClass, UnsafeConsumer<T> consumer, PaginationInfo paginationInfo) {
		try {
			getRepositoryFor(javaClass).listInto(javaClass, consumer, paginationInfo);
		} catch (LimitExceededException e) {
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Object getCi(ConfigurationItemReference reference) {
		ConfigurationItemDescriptor descriptor = metadataRegistry
				.getConfigurationItemDescriptorByName(reference.getConfigurationItemType());
		if (descriptor == null) {
			return null;
		}
		OrbagRepository repository = getRepositoryFor(descriptor.getJavaClass());
		return repository.getById(configurationItemReferenceService.getIdentifierFromReference(reference),
				descriptor.getJavaClass());
	}

	public List<?> getCis(List<ConfigurationItemReference> configurationItemReferences) {
		return configurationItemReferences.stream().map(this::getCi).filter(i -> i != null).toList();
	}

	public void delete(Object object) {
		OrbagRepository repository = getRepositoryFor(object.getClass());
		if (!(repository instanceof OrbagWritableRepository)) {
			throw new UnsupportedOperationException("Read only repository");
		}
		((OrbagWritableRepository) repository).delete(object);
	}

	public boolean isWritable(Object object) {
		OrbagRepository repository = getRepositoryFor(object.getClass());
		return repository instanceof OrbagWritableRepository;
	}

	public <T> void searchByConditionsInto(Class<T> javaClass, SearchConditions searchConditions,
			UnsafeConsumer<T> consumer, PaginationInfo paginationInfo) {
		try {
			OrbagRepository repository = getRepositoryFor(javaClass);
			if (!(repository instanceof OrbagSearcheableRepository)) {
				throw new UnsupportedOperationException("Search not allowed repository");
			}
			((OrbagSearcheableRepository) repository).searchByConditionsInto(javaClass, searchConditions, consumer,
					paginationInfo);
		} catch (LimitExceededException e) {
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
