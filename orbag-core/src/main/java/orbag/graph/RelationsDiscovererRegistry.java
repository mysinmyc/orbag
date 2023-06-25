package orbag.graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RelationsDiscovererRegistry {

    @Autowired(required = false)
    List<RelationsDiscoverer> relationsDiscoverers;

    public List<RelationsDiscoverer> getAllRelationsDiscoverers() {
        return relationsDiscoverers;
    }
}
