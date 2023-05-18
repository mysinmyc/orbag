package orbag.dao;


import orbag.data.PaginationInfo;
import orbag.metadata.Identifiable;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;


public interface OrbagRepository {

	boolean isManaged(Class<?> javaClass);

	default Object getIdentifier(Object ci) {
		if (ci ==null) {
			return null;
		}

		if (ci instanceof Identifiable<?>) {
			return ((Identifiable)ci).getIdentifier();
		}

		throw new UnsupportedOperationException();
	}

	<T> T getById(Object identifier, Class<T> javaClass);


}
