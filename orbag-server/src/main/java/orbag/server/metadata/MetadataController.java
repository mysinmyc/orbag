package orbag.server.metadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metadata")
public class MetadataController {

	@Autowired
	MetadataService metadataService;
	
	@GetMapping
	public GetClassModelResponse getClassModel() {
		
		GetClassModelResponse response = new GetClassModelResponse();
		response.setConfigurationItemDescriptors(metadataService.getConfigurationItemDescriptors());
		return response;
	}
}
