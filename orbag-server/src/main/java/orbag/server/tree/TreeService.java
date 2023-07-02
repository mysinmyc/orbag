package orbag.server.tree;

import orbag.dao.ConfigurationItemDao;
import orbag.dao.ConfigurationItemNotFoundException;
import orbag.graph.Path;
import orbag.graph.RelatedCisGraphBuilder;
import orbag.graph.SerializableGraphBuilder;
import orbag.metadata.UnmanagedObjectException;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.server.graph.GraphService;
import orbag.server.graph.SerializablePath;
import orbag.util.placeholder.PlaceholderConfigurationItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TreeService {

    @Autowired
    GraphService graphService;

    @Autowired
    ConfigurationItemReferenceService configurationItemReferenceService;

    @Autowired
    ConfigurationItemDao dao;

    TreeElement getRoot(ConfigurationItemReference configurationItemReference, Authentication user)  {
        TreeElement result = new TreeElement();
        result.setDisplayLabel(configurationItemReference.getDisplayLabel());
        result.setCi(configurationItemReference);
        return result;
    }

    public List<TreeElement> getAvailablePaths(TreeElement parent, Authentication user) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
        List<TreeElement> result = new ArrayList<>();
        for (SerializablePath currentPath : graphService.getAvailablePaths(parent.getCi(),user)) {
            TreeElement current = new TreeElement();
            current.setDisplayLabel(currentPath.getDisplayLabel());
            current.setPath(currentPath);
            current.setCi(parent.getCi());
            current.setFolder(true);
            result.add(current);
        }
        return result;
    }

    TreeElement buildChild(TreeElement parent, Object childConfigurationItem) throws UnmanagedObjectException {
        List<ConfigurationItemReference> previousSteps = new ArrayList<>();
        previousSteps.add(parent.getCi());
        if (parent.getPreviousSteps()!=null) {
            previousSteps.addAll(parent.getPreviousSteps());
        }
        ConfigurationItemReference currentConfigurationItemReference =configurationItemReferenceService.getReference(childConfigurationItem);
        TreeElement currentElement = new TreeElement();
        currentElement.setCi(currentConfigurationItemReference);
        currentElement.setPath(parent.getPath());
        currentElement.setDisplayLabel(currentConfigurationItemReference.getDisplayLabel());
        currentElement.setPreviousSteps(previousSteps);
        currentElement.setFolder( childConfigurationItem instanceof PlaceholderConfigurationItem);
        return currentElement;
    }

    List<TreeElement> buildChildrenFromRelatedCis(TreeElement parent, Object parentCi,Path path, RelatedCisGraphBuilder relatedCisGraphBuilder , int maxDepth) throws UnmanagedObjectException {
        List<TreeElement> result = new ArrayList<>();
        List<Object> relatedCis = relatedCisGraphBuilder.getRelatedCis(parentCi);
        if (relatedCis!=null) {
            for (Object relatedCi : relatedCis) {
                TreeElement currentChild = buildChild(parent,relatedCi);
                currentChild.setDestination(path.isDestination(relatedCi));
                if (maxDepth>0) {
                    currentChild.setChildren(buildChildrenFromRelatedCis(currentChild, relatedCi,path, relatedCisGraphBuilder,maxDepth-1).stream().filter(e -> !e.getCi().equals(parent.getCi())).toList());
                }
                result.add(currentChild);
            }
        }
        return  result;
    }

    public List<TreeElement> getChildren(TreeElement parent, Authentication user) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
        if (parent.getPath()==null){
            return getAvailablePaths(parent,user);
        }
        Path path=graphService.getPath(parent.getPath());
        if (path == null) {
            throw new UnmanagedObjectException("Invalid path " + parent.getPath().getIdentifier());
        }
        Object ci = dao.getCi(parent.getCi());
        RelatedCisGraphBuilder relatedCisGraphBuilder = new RelatedCisGraphBuilder();
        graphService.generateGraphInto(parent.getCi(),parent.getPath(),parent.getPreviousSteps(),user,relatedCisGraphBuilder);
        return buildChildrenFromRelatedCis(parent,ci,path,relatedCisGraphBuilder,5);
    }


}
