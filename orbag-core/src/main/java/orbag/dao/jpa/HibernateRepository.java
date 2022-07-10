package orbag.dao.jpa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import orbag.dao.OrbagWritableRepository;

@Component
public class HibernateRepository  implements OrbagWritableRepository{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public boolean isManaged(Class<?> configurationItemClass) {
		return configurationItemClass.getAnnotation(Entity.class) !=null;
	}

	@Override
	public <T> List<T> list(Class<T> configurationItemClass) {
		CriteriaQuery<T> query =entityManager.getCriteriaBuilder().createQuery(configurationItemClass);
		query.from(configurationItemClass);
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public <T> T getById(Object identifier, Class<T> javaClass) {
		return entityManager.find(javaClass, identifier);
	}

	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public void delete(Object object) {
		entityManager.remove(object);
	}

}
