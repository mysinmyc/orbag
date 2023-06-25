package orbag.samples.impacts;

import orbag.graph.GraphBuilder;
import orbag.graph.GraphGenerationContext;
import orbag.graph.RelationsDiscoverer;
import orbag.visibility.ManagedClasses;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@ManagedClasses(Infrastructure2BusinessAware.class)
public class Infrastructure2BuisinessRelationsDiscoverer implements RelationsDiscoverer {

    @Override
    public boolean isAvailableFor(Object configurationItem, GraphGenerationContext context) {
        return context.getPath() instanceof  InfrastructureToBusinessPath;
    }

    @Override
    public void discoverRelations(Object configurationItem, GraphBuilder graphBuilder, GraphGenerationContext context) {
        List<?> dependencies = ((Infrastructure2BusinessAware)configurationItem).getBusinessImpacts();
        if (dependencies!=null) {
            dependencies.stream().filter(Objects::nonNull).forEach(d -> graphBuilder.addRelation(configurationItem,d, "impact", "Impact"));
        }
    }
}
