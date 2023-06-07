package orbag.server.update;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import orbag.dao.ConfigurationItemNotFoundException;
import orbag.metadata.UnmanagedObjectException;
import orbag.server.ApiInfo;
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
@SecurityRequirements({@SecurityRequirement(name = ApiInfo.JWT)})
public class UpdateController {

	@Autowired
	UpdateService updateService;
	
	@PostMapping("/buildUpdateTemplate")
	public UpdateRequest buildUpdateTemplate(
			@RequestBody ConfigurationItemReference configurationItemReference, Authentication user) throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemNotFoundException {
		return updateService.getUpdateRequestTemplateFor(configurationItemReference, user);
	}
	
	@PostMapping("/execute")
	public ConfigurationItemReference update(@RequestBody UpdateRequest request, Authentication user) throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemNotFoundException {
		return updateService.update(request,user);
	}
}
