package orbag.server.create;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import orbag.metadata.UnmanagedObjectException;
import orbag.reference.ConfigurationItemReference;
import orbag.server.ApiInfo;
import orbag.server.util.ErrorPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbag.security.OrbagSecurityException;

@RestController
@RequestMapping("/api/create")
@SecurityRequirements({@SecurityRequirement(name = ApiInfo.JWT)})
public class CreateController {

	@Autowired
	CreateService createService;

	@Operation(summary = "Get configuration item creation template")
	@ApiResponses(
			{
					@ApiResponse(responseCode = "200", description = "OK"),
					@ApiResponse(responseCode = "400", description = "Invalid configuration item", content = @Content(schema = @Schema(implementation = ErrorPayload.class))),
					@ApiResponse(responseCode = "401", description = "Missing privileges")
			}
	)
	@GetMapping("/template/{configurationItemName}")
	public CreateRequest getCreateTemplate(
			@PathVariable("configurationItemName") String configurationItemName, Authentication user) throws OrbagSecurityException, UnmanagedObjectException {
		return createService.getCreateRequestTemplateFor(configurationItemName, user);
	}

	@Operation(summary = "Execute the configuration item creation")
	@ApiResponses(
			{
					@ApiResponse(responseCode = "200", description = "OK"),
					@ApiResponse(responseCode = "400", description = "Invalid configuration item", content = @Content(schema = @Schema(implementation = ErrorPayload.class))),
					@ApiResponse(responseCode = "401", description = "Creation denied due to missing privileges")
			}
	)
	@PostMapping("/execute")
	public ConfigurationItemReference create(@RequestBody CreateRequest request, Authentication user) throws OrbagSecurityException, UnmanagedObjectException {
		return  createService.create(request,user);
	}
}
