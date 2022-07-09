package orbag.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;

import org.springframework.stereotype.Component;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataProvider;

@Component
public class HibernateMetadataProvider implements MetadataProvider{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<ConfigurationItemDescriptor> getClassesMetadata() {
		List<ConfigurationItemDescriptor> classesMetadata = new ArrayList<>();
		for (EntityType<?> currentEntityType :  entityManager.getMetamodel().getEntities()) {
				ConfigurationItemDescriptor currentDescriptor = ConfigurationItemDescriptor.fromClass(currentEntityType.getJavaType());
				if (currentDescriptor!=null) {
					classesMetadata.add(currentDescriptor);
				}
		}
		return classesMetadata;
	}

	
}
