package orbag.samples.impacts;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import orbag.graph.GraphBuilder;
import orbag.graph.GraphGenerationContext;
import orbag.graph.RelationsDiscoverer;
import orbag.samples.cis.ApplicationInstance;
import orbag.samples.cis.BusinessApplication;
import orbag.samples.cis.BusinessProcess;
import orbag.visibility.ManagedClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@ManagedClasses(BusinessApplication.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessApplication2ProcessRelationsDiscoverer implements RelationsDiscoverer {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean isAvailableFor(Object configurationItem, GraphGenerationContext context) {
        return  context.getPath() instanceof InfrastructureToBusinessPath;
    }

    @Override
    public void discoverRelations(Object configurationItem, GraphBuilder graphBuilder, GraphGenerationContext context) {
        TypedQuery<BusinessProcess> businesProcessQuery = entityManager.createQuery( " select distinct b from "+BusinessProcess.class.getName()+" b left join fetch b.applications a where a.businessApplication=:application",BusinessProcess.class);
        businesProcessQuery.setParameter("application",configurationItem);

        for (BusinessProcess b : businesProcessQuery.getResultList()) {
            graphBuilder.addRelation(b,configurationItem, "use", "Use");
        }
    }
}
