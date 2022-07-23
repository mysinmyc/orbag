package orbag.server.edit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbag.reference.ConfigurationItemReferenceService;
import orbag.server.metadata.MetadataService;

@RestController
@RequestMapping("/api/edit")
public class EditController {

	@Autowired
	ConfigurationItemReferenceService  configurationItemReferenceService;
	
	@Autowired 
	MetadataService metadataService;
	
	@Autowired
	EditService editService;
	
	@RequestMapping("/reference/{configurationItemType}/{configurationItemId}")
	public GetConfigurationItemResponse getConfigurationItem(@PathVariable("configurationItemType") String configurationItemType,@PathVariable("configurationItemId") String configurationItemId) {
		GetConfigurationItemResponse response = new GetConfigurationItemResponse();
		
		Object configurationItem = editService.getConfiguratioItem(configurationItemType, configurationItemId);
		
		response.setReference(configurationItemReferenceService.getReference(configurationItem));
		response.setMetadata( metadataService.getSerializableConfigurationItemDescriptor(configurationItem.getClass()));
		response.setProperties(editService.getProperties(configurationItem));
		return response;
	}
}
