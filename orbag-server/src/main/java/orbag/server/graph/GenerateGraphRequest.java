package orbag.server.graph;

import orbag.reference.ConfigurationItemReference;

import java.util.List;

public class GenerateGraphRequest {

    ConfigurationItemReference startingCi;

    SerializablePath path;

    List<ConfigurationItemReference> previousSteps;

    public ConfigurationItemReference getStartingCi() {
        return startingCi;
    }

    public void setStartingCi(ConfigurationItemReference startingCi) {
        this.startingCi = startingCi;
    }

    public SerializablePath getPath() {
        return path;
    }

    public void setPath(SerializablePath path) {
        this.path = path;
    }

    public List<ConfigurationItemReference> getPreviousSteps() {
        return previousSteps;
    }

    public void setPreviousSteps(List<ConfigurationItemReference> previousSteps) {
        this.previousSteps = previousSteps;
    }
}
