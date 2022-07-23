package orbag.dao;

import orbag.data.PaginationInfo;
import orbag.util.UnsafeConsumer;


public interface OrbagRepository {

	boolean isManaged(Class<?> javaClass);
	
	<T> T getById(Object identifier, Class<T> javaClass);

	<T> void listInto(Class<T> javaClass, UnsafeConsumer<T> consumer, PaginationInfo paginationInfo) throws Exception;

}
