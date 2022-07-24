package orbag.server.search;

import org.springframework.stereotype.Component;

import orbag.dao.OrbagRepository;
import orbag.dao.OrbagSearcheableRepository;
import orbag.data.PaginationInfo;
import orbag.search.SearchConditions;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;

@Component
public class TestSearchRepository implements OrbagRepository, OrbagSearcheableRepository {

	@Override
	public boolean isManaged(Class<?> javaClass) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public <T> T getById(Object identifier, Class<T> javaClass) {
		try {
			T object =javaClass.newInstance();
			return object;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T> void listInto(Class<T> javaClass, UnsafeConsumer<T,LimitExceededException> consumer, PaginationInfo paginationInfo)
			throws LimitExceededException {
		// TODO Auto-generated method stub
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> void searchByConditionsInto(Class<T> configurationItemClass, SearchConditions searchConditions,
			UnsafeConsumer<T,LimitExceededException> consumer, PaginationInfo paginationInfo) throws LimitExceededException {
		
		if (TestSearchCi.class.isAssignableFrom(configurationItemClass)) {
			for (int cnt=0;cnt<10;cnt++) {
				TestSearchCi resultCi = new TestSearchCi();
				resultCi.setIdentifier("test "+cnt);
				consumer.accept((T)resultCi);
			}
		}
	}
}
