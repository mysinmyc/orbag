package orbag.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import orbag.EnableOrbagCore;
import orbag.data.PaginationInfo;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;

@SpringBootTest
@EnableOrbagCore
public class ConfigurationItemDaoTest {

	@Configuration
	static class ConfigurationClass {
		
	}
	
	@PersistedBy(RepositoryA.class)
	static class ClassA  {
		
	}
	
	@Component
	static class RepositoryA implements OrbagRepository {

		@Override
		public boolean isManaged(Class<?> javaClass) {
			return false;
		}

		@Override
		public <T> T getById(Object identifier, Class<T> javaClass) {
			return null;
		}

		@Override
		public <T> void listInto(Class<T> javaClass, UnsafeConsumer<T,LimitExceededException> consumer, PaginationInfo paginationInfo)
				throws LimitExceededException {
		}
		
	}

	
	@Test
	void testRepository(@Autowired ConfigurationItemDao dao) {
	
		assertInstanceOf(RepositoryA.class, dao.getRepositoryFor(ClassA.class));
	}

}
