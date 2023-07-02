package orbag.server.graph;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import orbag.dao.ConfigurationItemNotFoundException;
import orbag.graph.SerializableGraphBuilder;
import orbag.metadata.UnmanagedObjectException;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.server.ApiInfo;
import orbag.server.impact.GenerateReportRequest;
import orbag.server.impact.GenerateReportResponse;
import orbag.server.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/graph")
@SecurityRequirements({@SecurityRequirement(name = ApiInfo.JWT)})
public class GraphController {

    @Autowired
    GraphService graphService;

    @Autowired
    ConfigurationItemReferenceService referenceService;


    @PostMapping("/getAvailablePaths")
    public GetAvailablePathsResponse getAvailablePaths(@RequestBody GetAvailablePathsRequest request,
                                                       Authentication user) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
        GetAvailablePathsResponse response = new GetAvailablePathsResponse();
        response.setAvailablePaths(graphService.getAvailablePaths(request.getStartingCi(), user));
        return response;
    }

    @PostMapping("/generate")
    public GenerateGraphResponse generate(@RequestBody GenerateGraphRequest request, Authentication user) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
        SerializableGraphBuilder graphBuilder = new SerializableGraphBuilder(referenceService);
        graphService.generateGraphInto(request.getStartingCi(),request.getPath(),request.getPreviousSteps(),user, graphBuilder);
        GenerateGraphResponse response = new GenerateGraphResponse();
        response.setGraph(graphBuilder.build());
        return response;
    }

}
