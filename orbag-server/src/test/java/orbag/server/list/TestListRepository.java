package orbag.server.list;

import org.springframework.stereotype.Component;

import orbag.dao.OrbagRepository;
import orbag.data.PaginationInfo;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;

@Component
public class TestListRepository implements OrbagRepository {

	@Override
	public boolean isManaged(Class<?> javaClass) {
		return false;
	}

	@Override
	public <T> T getById(Object identifier, Class<T> javaClass) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void listInto(Class<T> javaClass, UnsafeConsumer<T,LimitExceededException> consumer, PaginationInfo paginationInfo)
			throws LimitExceededException {
		
		if (javaClass.equals(TestListCi.class)) {
			for (int cnt=0 ; cnt<paginationInfo.getSize();cnt++) {
				TestListCi ci = new TestListCi();
				ci.setIdentifier(cnt);
				consumer.accept((T)ci);
			}
		} else {
			throw new UnsupportedOperationException("Not implemented");
		}
	}

}
