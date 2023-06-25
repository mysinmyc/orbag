package orbag.graph;

import java.util.List;

public class SerializableGraph {

    List<SerializableRelation> relations;

    public List<SerializableRelation> getRelations() {
        return relations;
    }

    public void setRelations(List<SerializableRelation> relations) {
        this.relations = relations;
    }
}
