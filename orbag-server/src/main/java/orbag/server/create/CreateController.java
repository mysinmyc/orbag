package orbag.server.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbag.reference.ConfigurationItemReferenceExt;
import orbag.security.OrbagSecurityException;

@RestController
@RequestMapping("/api/create")
public class CreateController {

	@Autowired
	CreateService createService;
	
	@GetMapping("/template/{configurationItemName}")
	public CreateRequest getCreateTemplate(
			@PathVariable("configurationItemName") String configurationItemName, Authentication user) throws OrbagSecurityException {
		return createService.getCreateRequestTemplateFor(configurationItemName, user);
	}
	
	@PostMapping("/execute")
	public ConfigurationItemReferenceExt create(@RequestBody CreateRequest request, Authentication user) throws OrbagSecurityException {
		return  createService.create(request,user);
	}
}
