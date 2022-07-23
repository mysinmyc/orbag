package orbag.server.search;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.dao.OrbagRepository;
import orbag.dao.OrbagSearcheableRepository;
import orbag.data.PaginationInfo;
import orbag.metadata.MetadataRegistry;
import orbag.search.SearchConditions;
import orbag.util.UnsafeConsumer;

@Component
public class TestRepository implements OrbagRepository, InitializingBean, OrbagSearcheableRepository {

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
	public <T> void listInto(Class<T> javaClass, UnsafeConsumer<T> consumer, PaginationInfo paginationInfo)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Autowired
	MetadataRegistry metadataRegistry;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		metadataRegistry.setManagedClasses(TestCi.class);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void searchByConditionsInto(Class<T> configurationItemClass, SearchConditions searchConditions,
			UnsafeConsumer<T> consumer, PaginationInfo paginationInfo) throws Exception {
		
		if (TestCi.class.isAssignableFrom(configurationItemClass)) {
			for (int cnt=0;cnt<10;cnt++) {
				TestCi resultCi = new TestCi();
				resultCi.setIdentifier("test "+cnt);
				consumer.accept((T)resultCi);
			}
		}
	}
}
