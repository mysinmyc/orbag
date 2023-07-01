package orbag.util.placeholder;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
public @interface ManagedPlaceholder {

    String id_prefix();
}
