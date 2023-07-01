package orbag.server.graph;

import orbag.dao.ConfigurationItemDao;
import orbag.dao.ConfigurationItemNotFoundException;
import orbag.graph.*;
import orbag.metadata.UnmanagedObjectException;
import orbag.reference.ConfigurationItemReference;
import orbag.security.SecurityAssertionService;
import orbag.visibility.FilterContext;
import orbag.visibility.VisibilityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GraphService {

    @Autowired
    ConfigurationItemDao dao;

    @Autowired
    VisibilityManager visibilityManager;

    @Autowired
    PathRegistry pathRegistry;

    @Autowired
    SecurityAssertionService securityAssertionService;

    @Autowired
    RelationsDiscovererRegistry relationsDiscovererRegistry;


    public List<SerializablePath> getAvailablePaths(ConfigurationItemReference startingCiReference, Authentication user) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
        Object startingCi = dao.getCi(startingCiReference);
        List<SerializablePath> availablePaths  = new ArrayList<>();
        for( Path currentPath : pathRegistry.getAllPaths()) {
            if (visibilityManager.isObjectVisibile(currentPath, FilterContext.forTargetObject(startingCi).forUser(user),false)) {
                SerializablePath path = new SerializablePath();
                path.setIdentifier(currentPath.getIdentifier());
                path.setDisplayLabel(currentPath.getDisplayLabel());
                availablePaths.add(path);
            }
        }
        return availablePaths;
    }

    Path getPathFroId(String id) {
        return pathRegistry.getAllPaths().stream().filter( p -> p.getIdentifier().equals(id) ).findFirst().orElseGet(null);
    }


    public void generateGraphInto(ConfigurationItemReference startingCiReference, SerializablePath serializablePath, List<ConfigurationItemReference> previousSteps, Authentication user, GraphBuilder graphBuilder) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
        Path path=getPathFroId(serializablePath.getIdentifier());
        if (path == null) {
            throw new UnmanagedObjectException("Invalid path " + serializablePath.getIdentifier());
        }
        Object startingCi = dao.getExistingCiOrThrow(startingCiReference);
        GraphGenerationContext graphGenerationContext = new GraphGenerationContext();
        graphGenerationContext.setPath(path);
        graphGenerationContext.setUser(user);
        if (previousSteps!=null) {
            graphGenerationContext.setPreviousSteps(dao.getExistingCisOrThrow(previousSteps));
        }
        for (RelationsDiscoverer currentDiscoverer : visibilityManager.filterObjects(relationsDiscovererRegistry.getAllRelationsDiscoverers(), FilterContext.forTargetObject(startingCi).forUser(user))) {
            if (currentDiscoverer.isAvailableFor(startingCi, graphGenerationContext)) {
                currentDiscoverer.discoverRelations(startingCi, graphBuilder, graphGenerationContext);
            }
            if (graphBuilder.isComplete()) {
                break;
            }
        }
    }
}
