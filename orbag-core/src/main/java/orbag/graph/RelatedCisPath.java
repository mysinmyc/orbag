package orbag.graph;

import orbag.metadata.DisplayLabel;
import org.springframework.stereotype.Component;

@DisplayLabel("Related CIs")
@Component
public class RelatedCisPath extends PathBase{

    @Override
    public boolean isDestination(Object configurationItem) {
        return false;
    }
}
