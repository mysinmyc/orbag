package orbag.samples.impacts;

import orbag.graph.GraphBuilder;
import orbag.graph.GraphGenerationContext;
import orbag.graph.RelationsDiscoverer;
import orbag.samples.cis.BusinessProcess;
import orbag.samples.cis.ProductiveStage;
import orbag.util.placeholder.PlaceholderConfigurationItem;
import orbag.visibility.ManagedClasses;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@ManagedClasses(BusinessProcess.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessProcess2ProductiveStageRelationsDiscoverer implements RelationsDiscoverer {

    @Override
    public boolean isAvailableFor(Object configurationItem, GraphGenerationContext context) {
        return context.getPath() instanceof BusinessToInfrastructurePath;
    }

    @Override
    public void discoverRelations(Object configurationItem, GraphBuilder graphBuilder, GraphGenerationContext context) {
        for ( ProductiveStage current : ProductiveStage.values()) {
            graphBuilder.addRelation(configurationItem, new PlaceholderConfigurationItem("productiveStage/"+current.name(),current.name()),"productiveStage", "Productive stage");
        }
        graphBuilder.setComplete(true);
    }
}