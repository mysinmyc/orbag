package orbag.samples.impacts;

import orbag.graph.GraphBuilder;
import orbag.graph.GraphGenerationContext;
import orbag.graph.RelationsDiscoverer;
import orbag.samples.cis.ApplicationInstance;
import orbag.samples.cis.BusinessProcess;
import orbag.samples.cis.ProductiveStage;
import orbag.util.placeholder.ManagedPlaceholder;
import orbag.util.placeholder.PlaceholderConfigurationItem;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@ManagedPlaceholder(id_prefix = "productiveStage/")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessProcess_ProductiveStage_ApplicationsRelationsDiscoverer implements RelationsDiscoverer {

    @Override
    public boolean isAvailableFor(Object configurationItem, GraphGenerationContext context) {
        return context.getPath() instanceof BusinessToInfrastructurePath &&
                context.filterByPreviousSteps(e-> e instanceof BusinessProcess );
    }

    @Override
    public void discoverRelations(Object configurationItem, GraphBuilder graphBuilder, GraphGenerationContext context) {
        ProductiveStage productiveStage = ProductiveStage.valueOf(((PlaceholderConfigurationItem)configurationItem).getIdentifier().split("/")[1]);
        BusinessProcess businessProcess = (BusinessProcess) context.getPreviousSteps().get(0);
        for (ApplicationInstance currentApplication : businessProcess.getApplications()) {
            if (currentApplication.getServer() != null && productiveStage.equals(currentApplication.getServer().getProductiveStage()))
            {
                graphBuilder.addRelation(configurationItem, currentApplication, "depends_on", "Depends on");
            }
        }
        graphBuilder.setStepComplete(true);
    }
}