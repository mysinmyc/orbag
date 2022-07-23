package orbag.dao.jpa;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import orbag.dao.OrbagSearcheableRepository;
import orbag.dao.OrbagWritableRepository;
import orbag.data.PaginationInfo;
import orbag.search.SearchCondition;
import orbag.search.SearchConditions;
import orbag.util.UnsafeConsumer;

@Component
public class HibernateRepository implements OrbagWritableRepository, OrbagSearcheableRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public boolean isManaged(Class<?> configurationItemClass) {
		return configurationItemClass.getAnnotation(Entity.class) != null;
	}

	@Override
	public <T> void listInto(Class<T> configurationItemClass, UnsafeConsumer<T> consumer, PaginationInfo paginationInfo)
			throws Exception {
		CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(configurationItemClass);
		query.from(configurationItemClass);
		TypedQuery<T> executableQuery = entityManager.createQuery(query);
		executableQuery.setMaxResults(paginationInfo.getSize());
		executableQuery.setFirstResult(paginationInfo.getOffset());
		for (T object : entityManager.createQuery(query).getResultList()) {
			consumer.accept(object);
		}
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

	@SuppressWarnings("unchecked")
	protected Predicate createPredicateFromdCondition(Path<?> field, SearchCondition<?> condition,
			CriteriaBuilder criteriaBuilder) {

		switch (condition.getOperator()) {
		case EQUAL:
			return criteriaBuilder.equal(field, condition.getValue());
		case NOT_EQUAL:
			return criteriaBuilder.not(criteriaBuilder.equal(field, condition.getValue()));
		case LIKE:
			return criteriaBuilder.like((Path<String>) field, (String) condition.getValue());
		case NOT_LIKE:
			return criteriaBuilder.not(criteriaBuilder.like((Path<String>) field, (String) condition.getValue()));
		case GREATER:
			return criteriaBuilder.gt((Path<Number>) field, (Number) condition.getValue());
		case GREATER_OR_EQUAL:
			return criteriaBuilder.ge((Path<Number>) field, (Number) condition.getValue());
		case LESS:
			return criteriaBuilder.lt((Path<Number>) field, (Number) condition.getValue());
		case LESS_OR_EQUAL:
			return criteriaBuilder.le((Path<Number>) field, (Number) condition.getValue());
		default:
			return null;
		}

	}

	@Override
	public <T> void searchByConditionsInto(Class<T> configurationItemClass, SearchConditions searchConditions,
			UnsafeConsumer<T> consumer, PaginationInfo paginationInfo) throws Exception {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(configurationItemClass);
		Root<T> root = query.from(configurationItemClass);
		query.select(root);
		Predicate[] predicates = searchConditions.stream()
				.map(e -> createPredicateFromdCondition(root.get(e.getField()), e, criteriaBuilder)).toList()
				.toArray(new Predicate[] {});
		query.where(
				searchConditions.isAllRequired() ? criteriaBuilder.and(predicates) : criteriaBuilder.or(predicates));
		TypedQuery<T> executableQuery = entityManager.createQuery(query);
		executableQuery.setMaxResults(paginationInfo.getSize());
		executableQuery.setFirstResult(paginationInfo.getOffset());
		for (T object : entityManager.createQuery(query).getResultList()) {
			consumer.accept(object);
		}
	}

}
