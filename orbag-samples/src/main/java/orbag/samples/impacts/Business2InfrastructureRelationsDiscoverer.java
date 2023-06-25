package orbag.samples.impacts;

import orbag.graph.GraphBuilder;
import orbag.graph.GraphGenerationContext;
import orbag.graph.RelationsDiscoverer;
import orbag.visibility.ManagedClasses;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@ManagedClasses(Business2InfrastructureAware.class)
public class Business2InfrastructureRelationsDiscoverer implements RelationsDiscoverer {

    @Override
    public boolean isAvailableFor(Object configurationItem, GraphGenerationContext context) {
        return context.getPath() instanceof  BusinessToInfrastructurePath;
    }

    @Override
    public void discoverRelations(Object configurationItem, GraphBuilder graphBuilder, GraphGenerationContext context) {
        List<?> dependencies = ((Business2InfrastructureAware)configurationItem).getInfrastructuralDependencies();
        if (dependencies!=null) {
            dependencies.stream().filter(Objects::nonNull).forEach(d -> graphBuilder.addRelation(configurationItem,d, "depends_on", "Depends on"));
        }
    }
}
