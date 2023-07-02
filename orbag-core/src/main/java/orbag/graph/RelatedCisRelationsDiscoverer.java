package orbag.graph;

import orbag.metadata.*;
import orbag.security.AccessType;
import orbag.security.SecurityAssertionService;
import orbag.visibility.ManagedClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@ManagedClasses(Object.class)
public class RelatedCisRelationsDiscoverer implements RelationsDiscoverer {

    @Autowired
    SecurityAssertionService securityAssertionService;

    @Autowired
    MetadataRegistry metadataRegistry;

    @Override
    public boolean isAvailableFor(Object startingCis, GraphGenerationContext context) {
        return context.getPath() instanceof  RelatedCisPath;
    }

    @Override
    public void discoverRelations(Object startingCi, GraphBuilder graphBuilder, GraphGenerationContext context)  {
        try {
            ConfigurationItemDescriptor descriptor = metadataRegistry.getConfigurationItemDescriptorByClass(startingCi.getClass());
                for (ConfigurationItemPropertyDescriptor property : descriptor.getProperties()) {
                    if (property.isConfigurationItemReference() &&  securityAssertionService.hasAuthorizationToConfigurationItemPropertyDescriptor(property,context.getUser(), AccessType.READ) ) {
                        try {
                            Object relatedCi = property.getGetterMethod().invoke(startingCi);
                            if (relatedCi!=null) {
                                for (Object currentRelatedCi : ( relatedCi instanceof Collection ?  (Collection)relatedCi : List.of(relatedCi))) {
                                        if (context.getPreviousSteps().size()==0 || !context.getPreviousSteps().get(0).equals(relatedCi)) {
                                            graphBuilder.addRelation(startingCi, currentRelatedCi, property.getName(), property.getDisplayLabel());
                                        }
                                }
                            }
                        }catch (Exception e){
                        }
                    }
                }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
