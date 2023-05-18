package orbag.util;

import java.util.Collection;
import java.util.function.Function;

public interface UnsafeFunction<T,R,E extends Throwable>   {

	R apply(T var1);
	static <T,R, E extends Throwable> Function<T,R> wrap ( T type, UnsafeFunction<T,R,E> function)  {
		return (T value)->function.apply(value);
	}
}
