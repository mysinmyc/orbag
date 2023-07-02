package orbag.samples.impacts;

import orbag.graph.Path;
import orbag.graph.PathBase;
import orbag.metadata.DisplayLabel;
import orbag.samples.cis.Server;
import orbag.visibility.ManagedClasses;
import org.springframework.stereotype.Component;


@Component
@DisplayLabel("Business to infrastructure")
@ManagedClasses(Business2InfrastructureAware.class)
public class BusinessToInfrastructurePath extends PathBase {
    @Override
    public boolean isDestination(Object configurationItem) {
        return configurationItem instanceof Server;
    }
}
