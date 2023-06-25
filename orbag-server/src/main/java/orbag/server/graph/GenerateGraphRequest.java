package orbag.server.graph;

import orbag.reference.ConfigurationItemReference;

import java.util.List;

public class GenerateGraphRequest {

    public List<ConfigurationItemReference> getRootCis() {
        return rootCis;
    }

    public void setRootCis(List<ConfigurationItemReference> rootCis) {
        this.rootCis = rootCis;
    }

    List<ConfigurationItemReference> rootCis;

    SerializablePath path;

    public SerializablePath getPath() {
        return path;
    }

    public void setPath(SerializablePath path) {
        this.path = path;
    }
}
