package orbag.server.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbag.reference.ConfigurationItemReference;

@RestController
@RequestMapping("/api/update")
public class UpdateController {

	@Autowired
	UpdateService updateService;
	
	@GetMapping("/template/{configurationItemName}/{configurationItemId}")
	public UpdateRequest getCreateTemplate(
			@PathVariable("configurationItemName") String configurationItemName, 
			@PathVariable("configurationItemId") String configurationItemId, Authentication user) {
		return updateService.getUpdateRequestTemplateFor(configurationItemName,configurationItemId, user);
	}
	
	@PostMapping("/execute")
	public ConfigurationItemReference update(@RequestBody UpdateRequest request, Authentication user) {
		return updateService.update(request,user);
	}
}
