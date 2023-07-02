package orbag.server.tree;

import orbag.reference.ConfigurationItemReference;
import orbag.server.graph.SerializablePath;

import java.util.List;

public class TreeElement {


    String displayLabel;

    ConfigurationItemReference ci;

    SerializablePath path;

    List<ConfigurationItemReference> previousSteps;

    List<TreeElement> children;

    boolean folder;

    boolean destination;

    public String getDisplayLabel() {
        return displayLabel;
    }

    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    public ConfigurationItemReference getCi() {
        return ci;
    }

    public void setCi(ConfigurationItemReference ci) {
        this.ci = ci;
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

    public boolean isFolder() {
        return folder;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    public List<TreeElement> getChildren() {
        return children;
    }

    public void setChildren(List<TreeElement> children) {
        this.children = children;
    }

    public boolean isDestination() {
        return destination;
    }

    public void setDestination(boolean destination) {
        this.destination = destination;
    }
}
