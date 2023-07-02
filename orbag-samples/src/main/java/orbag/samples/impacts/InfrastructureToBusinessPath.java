package orbag.samples.impacts;

import orbag.graph.PathBase;
import orbag.metadata.DisplayLabel;
import orbag.samples.cis.Customer;
import orbag.samples.cis.Server;
import orbag.visibility.ManagedClasses;
import org.springframework.stereotype.Component;


@Component
@DisplayLabel("Business impacts")
@ManagedClasses({Infrastructure2BusinessAware.class, Infrastructure2BusinessDiscovered.class})
public class InfrastructureToBusinessPath extends PathBase {

    @Override
    public boolean isDestination(Object configurationItem) {
        return configurationItem instanceof Customer;
    }
}
