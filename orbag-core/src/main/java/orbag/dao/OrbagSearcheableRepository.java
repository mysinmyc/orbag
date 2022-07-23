package orbag.dao;

import orbag.data.PaginationInfo;
import orbag.search.SearchConditions;
import orbag.util.UnsafeConsumer;

public interface OrbagSearcheableRepository {

	<T> void searchByConditionsInto(Class<T> configurationItemClass, SearchConditions searchConditions, UnsafeConsumer<T> consumer, PaginationInfo paginationInfo) throws Exception;

}
