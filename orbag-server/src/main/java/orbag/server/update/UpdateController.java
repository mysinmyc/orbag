package orbag.server.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbag.reference.ConfigurationItemReference;
import orbag.security.OrbagSecurityException;

@RestController
@RequestMapping("/api/update")
public class UpdateController {

	@Autowired
	UpdateService updateService;
	
	@PostMapping("/getTemplate")
	public UpdateRequest getUpdateTemplate(
			@RequestBody ConfigurationItemReference configurationItemReference, Authentication user) throws OrbagSecurityException {
		return updateService.getUpdateRequestTemplateFor(configurationItemReference, user);
	}
	
	@PostMapping("/execute")
	public ConfigurationItemReference update(@RequestBody UpdateRequest request, Authentication user) throws OrbagSecurityException {
		return updateService.update(request,user);
	}
}
