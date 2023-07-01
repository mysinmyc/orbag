package orbag.util.placeholder;

import orbag.dao.PersistedBy;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;
import orbag.metadata.Displayable;
import orbag.metadata.Identifiable;

import java.util.Objects;

@ConfigurationItem(internal = true)
@PersistedBy(PlaceholderBackend.class)
public class PlaceholderConfigurationItem  implements Displayable, Identifiable<String> {

    String identifier;

    String displayLabel;

    public  PlaceholderConfigurationItem() {
    }

    public PlaceholderConfigurationItem(String identifier, String displayLabel) {
        this.identifier = identifier;
        this.displayLabel = displayLabel;
    }

    @ConfigurationItemProperty
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getDisplayLabel() {
        return displayLabel;
    }

    @ConfigurationItemProperty
    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceholderConfigurationItem that = (PlaceholderConfigurationItem) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
