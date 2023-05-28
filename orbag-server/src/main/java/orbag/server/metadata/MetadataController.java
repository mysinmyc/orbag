package orbag.server.metadata;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import orbag.metadata.UnmanagedObjectException;
import orbag.security.OrbagSecurityException;
import orbag.server.ApiInfo;
import orbag.server.util.ErrorPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metadata")
@SecurityRequirements({@SecurityRequirement(name = ApiInfo.JWT)})
public class MetadataController {

	@Autowired
	MetadataService metadataService;

	@Operation(summary = "Get class model")
	@GetMapping
	public GetClassModelResponse getClassModel(
			@RequestParam(name = "properties", defaultValue = "false") boolean includeProperties, Authentication user)  {
		GetClassModelResponse response = new GetClassModelResponse();
		response.setConfigurationItemDescriptors(metadataService.getConfigurationItemDescriptors(includeProperties, user));
		return response;
	}

	@GetMapping("/{configurationItemType}")
	public SerializableConfigurationItemDescriptor getClassMetadata(@PathVariable("configurationItemType") String configurationItemType, @RequestParam(name="properties",defaultValue = "true") boolean includeProperties , Authentication user) throws UnmanagedObjectException, OrbagSecurityException {
		return metadataService.getSerializableConfigurationItemDescriptor(configurationItemType,includeProperties, user);
	}
}
