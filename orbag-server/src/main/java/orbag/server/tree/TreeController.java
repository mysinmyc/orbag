package orbag.server.tree;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import orbag.dao.ConfigurationItemNotFoundException;
import orbag.metadata.UnmanagedObjectException;
import orbag.server.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tree")
@SecurityRequirements({@SecurityRequirement(name = ApiInfo.JWT)})
public class TreeController {

    @Autowired
    TreeService treeService;

    @PostMapping("/getRoot")
    public GetRootResponse getRoot(@RequestBody GetRootRequest request, Authentication user) {
        GetRootResponse response = new GetRootResponse();
        response.setRoot(treeService.getRoot(request.getCi(),user));
        return response;
    }

    @PostMapping("/getChildren")
    public GetChildrenResponse getChildren(@RequestBody GetChildrenRequest request, Authentication user) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
        GetChildrenResponse response = new GetChildrenResponse();
        response.setChildren(treeService.getChildren(request.getParent(),user));
        return response;
    }

}
