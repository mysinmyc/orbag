package orbag.graph;

import orbag.metadata.UnmanagedObjectException;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatedCisGraphBuilder implements GraphBuilder{

    Object startingCi;

    boolean stopBuild;

    Map<Object,List<Object>> relatedCis = new HashMap<>();

    public RelatedCisGraphBuilder() {
    }


    public void addRelatedCi(Object a,Object b) {
        List<Object> objectRelatedCis = relatedCis.get(a);
        if (objectRelatedCis==null)  {
            objectRelatedCis = new ArrayList<>();
            relatedCis.put(a,objectRelatedCis);
        }
        objectRelatedCis.add(b);
    }


    @Override
    public boolean addRelation(Object a, Object b, String relationName, String relationLabel) {
        addRelatedCi(a,b);
        addRelatedCi(b,a);
        return true;
    }

    public List<Object> getRelatedCis(Object startingCi) {
        return  relatedCis.get(startingCi);
    }

    @Override
    public boolean isStopBuild() {
        return stopBuild;
    }

    @Override
    public void setStopBuild(boolean stopBuild) {
        this.stopBuild = stopBuild;
    }
}
