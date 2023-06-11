package orbag.samples.cis;

import jakarta.persistence.Entity;
import orbag.metadata.ConfigurationItem;

@ConfigurationItem(category = "Test")
@Entity
public class SlowConfigurationItem extends RootConfigurationItem{
}
