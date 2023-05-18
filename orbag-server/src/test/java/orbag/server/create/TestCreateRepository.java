package orbag.server.create;

import org.springframework.stereotype.Component;

import orbag.dao.OrbagRepository;
import orbag.dao.OrbagWritableRepository;
import orbag.data.PaginationInfo;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;

@Component
public class TestCreateRepository implements OrbagRepository,  OrbagWritableRepository {

	@Override
	public boolean isManaged(Class<?> javaClass) {
		return false;
	}


	@Override
	public <T> T getById(Object identifier, Class<T> javaClass) {
		throw new UnsupportedOperationException("Not implemented");
	}


	@Override
	public void delete(Object object) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public <T> T create(T object) {
		return object;
	}

	@Override
	public <T> T update(T object) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented");
	}
}
