package orbag.server.action;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import orbag.dao.ConfigurationItemNotFoundException;
import orbag.metadata.UnmanagedObjectException;
import orbag.server.ApiInfo;
import orbag.server.util.ErrorPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbag.action.ActionResult;
import orbag.input.SerializableFieldGroup;
import orbag.security.OrbagSecurityException;

@RestController
@RequestMapping("/api/action")
@SecurityRequirements({@SecurityRequirement(name = ApiInfo.JWT)})
public class ActionController {

	@Autowired
	ActionService actionService;

	@Operation(summary = "Build available actions list",  description = "Return list of actions that user can executed on specified CIs")
	@ApiResponses(
			{
					@ApiResponse(responseCode = "200", description = "OK"),
					@ApiResponse(responseCode = "400", description = "Reference contains invalid objects", content = @Content(schema = @Schema(implementation = ErrorPayload.class))),
					@ApiResponse(responseCode = "404", description = "ConfigurationItemNotFound")
			}
	)
	@PostMapping("/getAvailable")
	public GetAvailableActionsResponse getAvailable(@RequestBody GetAvailableActionsRequest request,
														  Authentication user) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
		GetAvailableActionsResponse response = new GetAvailableActionsResponse();
		response.setAvailableActions(
				actionService.getAvaiableActionsFor(request.getSourceCi(), request.getTargetCis(), user));
		return response;
	}

	@Operation(summary = "Build action template", description = "Build the request template to execute specified action")
	@ApiResponses(
			{
					@ApiResponse(responseCode = "200", description = "OK"),
					@ApiResponse(responseCode = "400", description = "Reference contains invalid objects", content = @Content(schema = @Schema(implementation = ErrorPayload.class))),
					@ApiResponse(responseCode = "401", description = "Access denied"),
					@ApiResponse(responseCode = "404", description = "ConfigurationItemNotFound")
			}
	)
	@PostMapping("/buildExecutionTemplate")
	public SubmitActionRequest buildExecutionTemplate(@RequestBody BuildActionTemplateRequest buildActionTemplateRequest,
			Authentication user) throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemNotFoundException {
		SerializableFieldGroup parameters = new SerializableFieldGroup();
		actionService.buildParameters(buildActionTemplateRequest.getAction(), buildActionTemplateRequest.getSourceCi(),
				buildActionTemplateRequest.getTargetCis(), user, parameters);
		SubmitActionRequest response = new SubmitActionRequest();
		response.setAction(buildActionTemplateRequest.getAction());
		response.setTargetCis(buildActionTemplateRequest.getTargetCis());
		response.setSourceCi(buildActionTemplateRequest.getSourceCi());
		response.setParameters(parameters);
		return response;
	}

	@Operation(summary = "Submit the action")
	@ApiResponses(
			{
					@ApiResponse(responseCode = "200", description = "Action submitted"),
					@ApiResponse(responseCode = "400", description = "Invalid objects in request", content = @Content(schema = @Schema(implementation = ErrorPayload.class))),
					@ApiResponse(responseCode = "401", description = "Access denied"),
					@ApiResponse(responseCode = "404", description = "ConfigurationItemNotFound")
			}
	)
	@PostMapping("/submit")
	public SubmitActionResponse submit(@RequestBody SubmitActionRequest request, Authentication user)
			throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemNotFoundException {
		ActionResult result = actionService.submit(request.getAction(), request.getSourceCi(), request.getTargetCis(),
				request.getParameters(), user);
		SubmitActionResponse response = new SubmitActionResponse();
		response.setConsequences(result.getConsequences());
		response.setJobId(result.getJobId());
		response.setLink(result.getLink());
		response.setMessage(result.getMessage());
		response.setRequestValid(result.isRequestValid());
		response.setValidationErrors(result.getValidationErrors());
		return response;
	}
}
