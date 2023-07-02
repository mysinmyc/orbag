package orbag.server.search;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import orbag.data.*;
import orbag.metadata.UnmanagedObjectException;
import orbag.server.ApiInfo;
import orbag.util.OneTimeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import orbag.reference.ConfigurationItemReferenceService;
import orbag.security.OrbagSecurityException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/search")
@SecurityRequirements({@SecurityRequirement(name = ApiInfo.JWT)})
public class SearchController {

	@Autowired
	SearchService searchService;

	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;

	@Autowired
	OneTimeCache oneTimeCache;

	@GetMapping("/template/{configurationItemName}")
	public SearchRequest getSearchTemplate(
			@PathVariable("configurationItemName") String configurationItemName, Authentication user) throws OrbagSecurityException, UnmanagedObjectException {
		return searchService.getSearchRequestTemplateFor(configurationItemName, user);
	}

	@PostMapping("/execute")
	public SerializableTable execute(@RequestBody SearchRequest request,
			@RequestParam(defaultValue = "50", name = "limit") Integer limit,
			@RequestParam(defaultValue = "0", name = "offset") Integer offset, Authentication user) throws OrbagSecurityException, UnmanagedObjectException {
		SerializableTableBuilder<Object> serializableTableBuilder = new SerializableTableBuilder<>(
				configurationItemReferenceService);
		searchService.executeSearchInto(request, user, new PaginationInfo(limit,offset), serializableTableBuilder);
		return serializableTableBuilder.build();
	}

	@PostMapping("/executeLater")
	public ExecuteLaterResponse executeLater(@RequestBody SearchRequest request, Authentication user) {
		ExecuteLaterResponse response = new ExecuteLaterResponse();
		response.setSearchId(oneTimeCache.putUserData(user,request));
		return response;
	}

	@GetMapping(value = "/execute/{searchId}.tsv", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
	public void exportTsv(@PathVariable("searchId") String id, ServletResponse response, Authentication user) throws Exception {
		SearchRequest request=oneTimeCache.getUserData(user,id);
		((HttpServletResponse)response).setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Search_"+request.getConfigurationItemName()+"_"+ new SimpleDateFormat("yyyy-MM-dd_hhmm").format(new Date()) +".tsv");
		try (OutputStream outputStream = response.getOutputStream(); OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream); TsvTableBuilder<Object> tsvTableBuilder = new TsvTableBuilder<>(outputStreamWriter)) {
			tsvTableBuilder.addGeneratedColumn("Configuration Item", ColumnType.Reference,o -> o);
			searchService.executeSearchInto(request, user, new PaginationInfo(), tsvTableBuilder);
		}
	}

}
