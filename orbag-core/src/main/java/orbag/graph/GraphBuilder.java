package orbag.graph;

public interface GraphBuilder {

    boolean addRelation(Object a, Object b, String relationName, String relationLabel);
}