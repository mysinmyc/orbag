package orbag.graph;

import orbag.metadata.Describable;
import orbag.metadata.Displayable;

public interface Path extends Displayable {

    String getIdentifier();

    boolean isArrivalConfigurationItem(Object configurationItem);
}
