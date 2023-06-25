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

    boolean isPathVisibleForAtLeastForOneCi(Path path, List<?> configurationItems, Authentication user) {
        for (Object ci : configurationItems) {
            if (visibilityManager.isObjectVisibile(path, FilterContext.forTargetObject(ci).forUser(user),false)) {
                return true;
            }
        }
        return false;
    }

    public List<SerializablePath> getAvailablePaths(List<ConfigurationItemReference> rootCiReferences, Authentication user) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
        List<?> rootCis = dao.getExistingCisOrThrow(rootCiReferences);
        List<SerializablePath> availablePaths  = new ArrayList<>();
        for( Path currentPath : pathRegistry.getAllPaths()) {
            if (isPathVisibleForAtLeastForOneCi(currentPath,rootCis,user)) {
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


    public void generateGraphInto(List<ConfigurationItemReference> rootCiReferences, SerializablePath serializablePath, Authentication user, GraphBuilder graphBuilder) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
        Path path=getPathFroId(serializablePath.getIdentifier());
        if (path == null) {
            throw new UnmanagedObjectException("Invalid path " + serializablePath.getIdentifier());
        }
        List<?> rootCis = dao.getExistingCisOrThrow(rootCiReferences);
        GraphGenerationContext graphGenerationContext = new GraphGenerationContext();
        graphGenerationContext.setPath(path);
        graphGenerationContext.setUser(user);
        for (Object ci : rootCis) {
            for (RelationsDiscoverer currentDiscoverer : visibilityManager.filterObjects(relationsDiscovererRegistry.getAllRelationsDiscoverers(), FilterContext.forTargetObject(ci).forUser(user))) {
                if (currentDiscoverer.isAvailableFor(ci, graphGenerationContext)) {
                    currentDiscoverer.discoverRelations(ci, graphBuilder, graphGenerationContext);
                }
            }
        }
    }
}
