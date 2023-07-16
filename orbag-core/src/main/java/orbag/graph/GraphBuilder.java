package orbag.graph;

public interface GraphBuilder {

    boolean addRelation(Object fromCi, Object toCi, String relationName, String relationLabel);

    boolean isStopBuild();

    void setStopBuild(boolean stopBuild);
}
