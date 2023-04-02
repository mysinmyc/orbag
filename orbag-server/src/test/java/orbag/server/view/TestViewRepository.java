package orbag.server.view;

import org.springframework.stereotype.Component;

import orbag.dao.OrbagRepository;
import orbag.data.PaginationInfo;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;

@Component
public class TestViewRepository implements OrbagRepository  {

	

	@Override
	public boolean isManaged(Class<?> javaClass) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Object identifier, Class<T> javaClass) {
		if ( javaClass.equals(TestViewCi.class)) {
			TestViewCi result = new TestViewCi();
			result.setIdentifier(identifier.toString());
			return (T) result;
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> void listInto(Class<T> javaClass, UnsafeConsumer<T,LimitExceededException> consumer, PaginationInfo paginationInfo)
			throws LimitExceededException {
		throw new UnsupportedOperationException("Not implemented");
	}


}
