package orbag.orchestration;

import orbag.dao.OrbagRepository;
import orbag.data.PaginationInfo;
import orbag.orchestration.tools.ascob.AscobClient;
import orbag.util.LimitExceededException;
import orbag.util.UnsafeConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AscobRepository implements OrbagRepository {

    @Autowired
    AscobClient ascobClient;

    @Override
    public boolean isManaged(Class<?> javaClass) {
        return javaClass.equals(RunInfo.class);
    }

    @Override
    public Object getIdentifier(Object ci) {
        if ( ci.getClass().equals(RunInfo.class)) {
            return ((RunInfo)ci).getIdentifier();
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getById(Object identifier, Class<T> javaClass) {
        if ( javaClass.equals(RunInfo.class)) {
            return (T) ascobClient.getRunInfo(Long.parseLong(identifier.toString()));
        }
        throw new UnsupportedOperationException();
    }

}
