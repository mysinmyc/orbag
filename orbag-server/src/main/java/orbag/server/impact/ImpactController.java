package orbag.server.impact;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import orbag.server.ApiInfo;
import orbag.server.search.SearchRequest;
import orbag.util.OneTimeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/impact")
@SecurityRequirements({@SecurityRequirement(name = ApiInfo.JWT)})
public class ImpactController {

    @Autowired
    OneTimeCache oneTimeCache;
    @PostMapping("/generateReportLater")
    public GenerateReportResponse generateReportLater(@RequestBody GenerateReportRequest request, Authentication user) {
        GenerateReportResponse response = new GenerateReportResponse();
        response.setReportId(oneTimeCache.putUserData(user,request));
        return response;
    }

    @GetMapping(value="/generateReport/{reportId}.xlsx", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public void generateReportXlsx(@PathVariable("reportId") String id, ServletResponse response, Authentication user) {
        SearchRequest request=oneTimeCache.getUserData(user,id);
        ((HttpServletResponse)response).setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ImpactReport_"+request.getConfigurationItemName()+"_"+ new SimpleDateFormat("yyyy-MM-dd_hhmm").format(new Date()) +".xlsx");

    }
}
