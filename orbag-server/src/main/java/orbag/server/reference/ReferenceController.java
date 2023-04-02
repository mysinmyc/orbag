package orbag.server.reference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbag.reference.ConfigurationItemReference;

@RestController
@RequestMapping("/api/reference")
public class ReferenceController {

	@Autowired
	ReferenceService referenceService;

	@RequestMapping("/{configurationItemType}/{configurationItemId}")
	public ConfigurationItemReference getConfigurationItem(
			@PathVariable("configurationItemType") String configurationItemType,
			@PathVariable("configurationItemId") String configurationItemId) {
		return referenceService.loadReference(configurationItemId, configurationItemType);
	}
}
