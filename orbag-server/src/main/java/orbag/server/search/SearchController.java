package orbag.server.search;

import orbag.metadata.UnmanagedObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import orbag.data.PaginationInfo;
import orbag.data.SerializableTable;
import orbag.data.SerializableTableBuilder;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.security.OrbagSecurityException;

@RestController
@RequestMapping("/api/search")
public class SearchController {

	@Autowired
	SearchService searchService;

	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;

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
}
