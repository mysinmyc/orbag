package orbag.server.reference;

import orbag.dao.ConfigurationItemNotFoundException;
import orbag.metadata.UnmanagedObjectException;
import orbag.security.OrbagSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import orbag.reference.ConfigurationItemReference;

@RestController
@RequestMapping("/api/reference")
public class ReferenceController {

	@Autowired
	ReferenceService referenceService;

	@RequestMapping(value = "/{configurationItemType}/{configurationItemId}", method = RequestMethod.GET)
	public ConfigurationItemReference getConfigurationItem(
			@PathVariable("configurationItemType") String configurationItemType,
			@PathVariable("configurationItemId") String configurationItemId,
			Authentication user) throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemNotFoundException {
		return referenceService.loadReference(configurationItemId, configurationItemType, user);
	}
}
