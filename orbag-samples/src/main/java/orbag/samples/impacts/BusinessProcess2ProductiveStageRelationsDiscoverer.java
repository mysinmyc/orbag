package orbag.samples.impacts;

import orbag.graph.GraphBuilder;
import orbag.graph.GraphGenerationContext;
import orbag.graph.RelationsDiscoverer;
import orbag.samples.cis.ApplicationInstance;
import orbag.samples.cis.BusinessProcess;
import orbag.samples.cis.ProductiveStage;
import orbag.util.placeholder.PlaceholderConfigurationItem;
import orbag.visibility.ManagedClasses;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

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
        BusinessProcess businessProcess = (BusinessProcess)configurationItem;
        Set<ProductiveStage> stages = new HashSet<>();
        for (ApplicationInstance currentApplication : businessProcess.getApplications()) {
            if (currentApplication.getServer()!=null && currentApplication.getServer().getProductiveStage()!=null) {
                stages.add(currentApplication.getServer().getProductiveStage());
            }
        }
        for (ProductiveStage productiveStage : stages) {
            graphBuilder.addRelation(configurationItem, new PlaceholderConfigurationItem("productiveStage/"+productiveStage.name(),productiveStage.name()),"productiveStage", "Productive stage");
        }
        graphBuilder.setStopBuild(true);
    }
}