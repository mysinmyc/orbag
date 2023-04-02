package orbag.impl.cis;

import jakarta.persistence.Entity;
import orbag.metadata.ConfigurationItem;

@ConfigurationItem(category = "After work", allowCreation = false, readOnly=true)
@Entity
public class BreweryMenuItem extends RootConfigurationItem{

	
}
