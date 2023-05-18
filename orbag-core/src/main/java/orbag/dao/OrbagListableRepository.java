package orbag.dao;

import orbag.data.PaginationInfo;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;

public interface OrbagListableRepository {
    <T> void listInto(Class<T> javaClass, UnsafeConsumer<T, LimitExceededException> consumer, PaginationInfo paginationInfo) throws LimitExceededException;

}
