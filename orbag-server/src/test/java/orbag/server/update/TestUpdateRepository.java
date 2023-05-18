package orbag.server.update;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import orbag.dao.OrbagRepository;
import orbag.dao.OrbagWritableRepository;
import orbag.data.PaginationInfo;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;

@Component
public class TestUpdateRepository implements OrbagRepository,  OrbagWritableRepository {


	Map<String,TestUpdateCi> cis = new HashMap<>();
	
	@Override
	public boolean isManaged(Class<?> javaClass) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Object identifier, Class<T> javaClass) {
		if ( javaClass.equals(TestUpdateCi.class)) {
			return (T) cis.get(identifier.toString());
		}
		throw new UnsupportedOperationException();
	}


	@Override
	public void delete(Object object) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public <T> T create(T object) {
		if ( object  instanceof TestUpdateCi) {
			cis.put(((TestUpdateCi)object).getIdentifier(), (TestUpdateCi) object);
			return object;
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T update(T object) {
		if ( object  instanceof TestUpdateCi) {
			cis.put(((TestUpdateCi)object).getIdentifier(), (TestUpdateCi) object);
			return object;
		}
		throw new UnsupportedOperationException();
	}
}
