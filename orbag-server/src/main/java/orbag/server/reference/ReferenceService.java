package orbag.server.reference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceExt;
import orbag.reference.ConfigurationItemReferenceService;

@Component
public class ReferenceService {

	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;

	@Autowired
	ConfigurationItemDao dao;

	public ConfigurationItemReferenceExt loadReference(String configurationItemId, String configurationItemType) {
		return configurationItemReferenceService
				.getReference(dao.getCi(new ConfigurationItemReference(configurationItemId, configurationItemType)));
	}
}
