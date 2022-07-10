package orbag.dao;

import java.util.List;


public interface OrbagRepository {

	boolean isManaged(Class<?> javaClass);
	
	<T> List<T> list(Class<T> javaClass);

	Object getById(Long identifier, Class<?> javaClass);

	void delete(Object object);
}
