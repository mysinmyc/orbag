package orbag.server.graph;

import orbag.graph.SerializableGraph;

public class GenerateGraphResponse {

    SerializableGraph graph;

    public SerializableGraph getGraph() {
        return graph;
    }

    public void setGraph(SerializableGraph graph) {
        this.graph = graph;
    }
}

