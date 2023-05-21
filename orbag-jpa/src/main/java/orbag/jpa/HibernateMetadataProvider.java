package orbag.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.metamodel.EntityType;

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
				ConfigurationItemDescriptor currentDescriptor = ConfigurationItemDescriptor.fromClass(currentEntityType.getJavaType(),currentEntityType.getIdType() ==null ? Object.class: currentEntityType.getIdType().getJavaType(),true);
				if (currentDescriptor!=null) {
					classesMetadata.add(currentDescriptor);
				}
		}
		return classesMetadata;
	}

	
}
