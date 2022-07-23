package orbag.util;

import java.util.Collection;

public interface UnsafeConsumer<T> {
	
	void accept(T object) throws Exception;

	default void acceptAll(Collection<T> objects) throws Exception {
		if (objects!=null) {
			for (T object : objects) {
				accept(object);
			}
		}
	}
}
