package orbag.util;

import java.util.function.BiConsumer;

public interface UnsafeBiConsumer<T, U, E extends Throwable> {

        void accept(T var1, U var2) throws E;
}