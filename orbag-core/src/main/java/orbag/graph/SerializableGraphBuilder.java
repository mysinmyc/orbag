package orbag.graph;

import orbag.reference.ConfigurationItemReferenceService;

import java.util.ArrayList;
import java.util.List;

public class SerializableGraphBuilder implements  GraphBuilder{

    boolean complete;

    ConfigurationItemReferenceService configurationItemReferenceService;

    SerializableGraph result = new SerializableGraph();

    public SerializableGraphBuilder(ConfigurationItemReferenceService configurationItemReferenceService) {
        this.configurationItemReferenceService = configurationItemReferenceService;
    }

    @Override
    public boolean addRelation(Object startingCi, Object endCi, String relationName, String relationLabel) {
        try {
            SerializableRelation relation = new SerializableRelation();
            relation.setStartingCi(configurationItemReferenceService.getReference(startingCi));
            relation.setEndCi(configurationItemReferenceService.getReference(endCi));
            relation.setName(relationName);
            relation.setDisplayLabel(relationLabel);
            if (result.getRelations()==null) {
                result.setRelations(new ArrayList<>());
            }
            result.getRelations().add(relation);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    @Override
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public SerializableGraph build() {
        return result;
    }
}
