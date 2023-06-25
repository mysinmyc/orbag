package orbag.graph;

import java.util.List;

public interface RelationsDiscoverer {

    default boolean isAvailableFor(Object configurationItem, GraphGenerationContext context) {
        return true;
    }

    void discoverRelations(Object configurationItem, GraphBuilder graphBuilder, GraphGenerationContext context);
}
