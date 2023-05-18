package orbag.orchestration;

import orbag.metadata.ConfigurationItem;

@ConfigurationItem(category = "Orchestration", displayLabel = "Flow run")
public class RunInfo implements orbag.metadata.Identifiable<Long>, orbag.metadata.Displayable {

    Long id;

    @Override
    public Long getIdentifier() {
        return id;
    }
}
