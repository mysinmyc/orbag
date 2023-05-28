package orbag.server.list;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import orbag.metadata.UnmanagedObjectException;
import orbag.server.ApiInfo;
import orbag.server.util.ErrorPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import orbag.data.PaginationInfo;
import orbag.security.OrbagSecurityException;

@RestController
@RequestMapping("/api/list")
@SecurityRequirements({@SecurityRequirement(name = ApiInfo.JWT)})
public class ListController {

	@Autowired
	ListService listService;

	@Operation(description = "List configuration items of the specified type")
	@ApiResponses(
			{
					@ApiResponse(responseCode = "200", description = "OK"),
					@ApiResponse(responseCode = "400", description = "Invalid configuration item", content = @Content(schema = @Schema(implementation = ErrorPayload.class))),
					@ApiResponse(responseCode = "401", description = "List denied due to missing privileged")
			}
	)
	@GetMapping("/{configurationItemName}")
	public ListConfigurationItemResponse listConfigurationItems(
			@PathVariable("configurationItemName") String configurationItemName,
			@RequestParam(defaultValue = "50", name = "limit") Integer limit,
			@RequestParam(defaultValue = "0", name = "offset") Integer offset,
			Authentication user) throws OrbagSecurityException, UnmanagedObjectException {
		ListConfigurationItemResponse response = new ListConfigurationItemResponse();
		response.setCis(listService.list(configurationItemName, new PaginationInfo(limit,offset),user));
		return response;
	}
}
