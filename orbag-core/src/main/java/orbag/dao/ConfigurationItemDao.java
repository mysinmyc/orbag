package orbag.dao;

import java.util.ArrayList;
import java.util.List;

import orbag.metadata.UnmanagedObjectException;
import orbag.util.UnsafeFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.data.PaginationInfo;
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataRegistry;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.search.SearchCondition;
import orbag.search.SearchConditions;
import orbag.util.LimitExceededException;
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
				.orElseThrow(() -> new RuntimeException("No repository available for " + javaClass.getName()));
	}

	public <T> void listInto(Class<T> javaClass, UnsafeConsumer<T,LimitExceededException> consumer, PaginationInfo paginationInfo) {

		try {
			OrbagRepository repository = getRepositoryFor(javaClass);
			if (!(repository instanceof OrbagListableRepository)) {
				throw new UnsupportedOperationException("List not allowed in repository");
			}
			((OrbagListableRepository) repository).listInto(javaClass, consumer, paginationInfo);
		} catch (LimitExceededException e) {
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Object getCi(ConfigurationItemReference reference) throws UnmanagedObjectException {
		if (reference==null) {
			return null;
		}
		ConfigurationItemDescriptor descriptor = metadataRegistry
				.getConfigurationItemDescriptorByName(reference.getConfigurationItemType());
		if (descriptor == null) {
			throw new UnmanagedObjectException();
		}
		OrbagRepository repository = getRepositoryFor(descriptor.getJavaClass());
		return repository.getById(configurationItemReferenceService.getIdentifierFromReference(reference),
				descriptor.getJavaClass());
	}

	public Object getExistingCiOrThrow(ConfigurationItemReference reference) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
		Object ci = getCi(reference);
		if (ci==null) {
			throw new ConfigurationItemNotFoundException();
		}
		return ci;
	}

	public Object getIdentifier(Object ci) throws UnmanagedObjectException {
		if (ci==null) {
			return null;
		}
		ConfigurationItemDescriptor descriptor = metadataRegistry
				.getConfigurationItemDescriptorByClass(ci.getClass());
		if (descriptor == null) {
			throw new UnmanagedObjectException();
		}
		OrbagRepository repository = getRepositoryFor(descriptor.getJavaClass());
		return repository.getIdentifier(ci);
	}


	public  List<?> getCis(List<ConfigurationItemReference> configurationItemReferences) throws UnmanagedObjectException {
		List<Object> result = new ArrayList<>();
		for (ConfigurationItemReference currentReference : configurationItemReferences) {
			Object ci = getCi(currentReference);
			result.add(ci);
		}
		return result;
	}

	public  List<?> getExistingCisOrThrow(List<ConfigurationItemReference> configurationItemReferences) throws UnmanagedObjectException,ConfigurationItemNotFoundException {
		List<Object> result = new ArrayList<>();
		for (ConfigurationItemReference currentReference : configurationItemReferences) {
			Object ci = getExistingCiOrThrow(currentReference);
			result.add(ci);
		}
		return result;
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

	public <T> void searchByConditionsInto(Class<T> javaClass,  UnsafeConsumer<T,LimitExceededException> consumer, SearchCondition<?>... conditions) {
		searchByConditionsInto(javaClass, SearchConditions.and(conditions), consumer, new PaginationInfo());
	}
	
	public <T> void searchByConditionsInto(Class<T> javaClass, SearchConditions searchConditions,
			UnsafeConsumer<T,LimitExceededException> consumer, PaginationInfo paginationInfo) {
		try {
			OrbagRepository repository = getRepositoryFor(javaClass);
			if (!(repository instanceof OrbagSearcheableRepository)) {
				throw new UnsupportedOperationException("Search not allowed in repository");
			}
			((OrbagSearcheableRepository) repository).searchByConditionsInto(javaClass, searchConditions, consumer,
					paginationInfo);
		} catch (LimitExceededException e) {
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Object create(Object newObject) {
		OrbagRepository repository = getRepositoryFor(newObject.getClass());
		if (!(repository instanceof OrbagWritableRepository)) {
			throw new UnsupportedOperationException("Read only repository");
		}
		return ((OrbagWritableRepository) repository).create(newObject);
	}

	public Object update(Object object) {
		OrbagRepository repository = getRepositoryFor(object.getClass());
		if (!(repository instanceof OrbagWritableRepository)) {
			throw new UnsupportedOperationException("Read only repository");
		}
		return ((OrbagWritableRepository) repository).update(object);
	}

}
