package orbag.graph;

import orbag.reference.ConfigurationItemReference;

public class SerializableRelation {

    String name;

    String displayLabel;

    ConfigurationItemReference startingCi;

    ConfigurationItemReference endCi;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConfigurationItemReference getStartingCi() {
        return startingCi;
    }

    public void setStartingCi(ConfigurationItemReference startingCi) {
        this.startingCi = startingCi;
    }

    public ConfigurationItemReference getEndCi() {
        return endCi;
    }

    public void setEndCi(ConfigurationItemReference endCi) {
        this.endCi = endCi;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }

    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }
}
