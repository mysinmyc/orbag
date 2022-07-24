package orbag.dao;


import orbag.data.PaginationInfo;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;


public interface OrbagRepository {

	boolean isManaged(Class<?> javaClass);
	
	<T> T getById(Object identifier, Class<T> javaClass);

	<T> void listInto(Class<T> javaClass, UnsafeConsumer<T,LimitExceededException> consumer, PaginationInfo paginationInfo) throws LimitExceededException;

}
