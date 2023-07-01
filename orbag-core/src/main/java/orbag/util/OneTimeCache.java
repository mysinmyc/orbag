package orbag.util;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OneTimeCache {

    Map<String,Object> map= new ConcurrentHashMap<>();

    String buildInternalId(Authentication user, String id) {
        return user.getName()+"|"+id;
    }

    public String putUserData(Authentication user, Object data) {

        String id = UUID.randomUUID().toString();
        map.put(buildInternalId(user,id),data);
        return id;
    }

    public <T> T getUserData(Authentication user, String id) {
        return (T) map.remove(buildInternalId(user,id));
    }
}
