package orbag.util.placeholder;

import orbag.dao.OrbagListableRepository;
import orbag.dao.OrbagRepository;
import orbag.data.PaginationInfo;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;
import org.springframework.stereotype.Component;

@Component
public class PlaceholderBackend implements OrbagRepository {

    @Override
    public boolean isManaged(Class<?> javaClass) {
        return false;
    }

    @Override
    public <T> T getById(Object identifier, Class<T> javaClass) {
        return (T) new PlaceholderConfigurationItem((String)identifier,(String)identifier);
    }
}
