package orbag.server.graph;

import java.util.List;

public class GetAvailablePathsResponse {

    List<SerializablePath> availablePaths;

    public List<SerializablePath> getAvailablePaths() {
        return availablePaths;
    }

    public void setAvailablePaths(List<SerializablePath> availablePaths) {
        this.availablePaths = availablePaths;
    }

}
