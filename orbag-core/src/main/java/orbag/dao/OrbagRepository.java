package orbag.dao;

import java.util.List;


public interface OrbagRepository {

	boolean isManaged(Class<?> javaClass);
	
	<T> List<T> list(Class<T> javaClass);

	<T> T getById(Object identifier, Class<T> javaClass);


}
