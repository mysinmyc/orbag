package orbag.util;

import orbag.metadata.UnmanagedObjectException;

import java.util.Collection;

public interface UnsafeConsumer<T,E extends Throwable> {

	void accept(T object) throws E;

	default void acceptAll(Collection<T> objects) throws E {
		if (objects!=null) {
			for (T object : objects) {
				accept(object);
			}
		}
	}
}
