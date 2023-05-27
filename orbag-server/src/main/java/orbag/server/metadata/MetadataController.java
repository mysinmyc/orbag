package orbag.server.metadata;

import orbag.metadata.UnmanagedObjectException;
import orbag.security.OrbagSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metadata")
public class MetadataController {

	@Autowired
	MetadataService metadataService;

	@GetMapping
	public GetClassModelResponse getClassModel(
			@RequestParam(name = "properties", defaultValue = "false") boolean includeProperties, Authentication user) throws OrbagSecurityException {
		GetClassModelResponse response = new GetClassModelResponse();
		response.setConfigurationItemDescriptors(metadataService.getConfigurationItemDescriptors(includeProperties, user));
		return response;
	}

	@GetMapping("/{configurationItemType}")
	public SerializableConfigurationItemDescriptor getClassMetadata(@PathVariable("configurationItemType") String configurationItemType, @RequestParam(name="properties",defaultValue = "true") boolean includeProperties , Authentication user) throws UnmanagedObjectException, OrbagSecurityException {
		return metadataService.getSerializableConfigurationItemDescriptor(configurationItemType,includeProperties, user);
	}
}
