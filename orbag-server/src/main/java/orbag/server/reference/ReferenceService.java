package orbag.server.reference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;

@Component
public class ReferenceService {

	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;

	@Autowired
	ConfigurationItemDao dao;

	public ConfigurationItemReference loadReference(String configurationItemId, String configurationItemType) {
		return (ConfigurationItemReference) configurationItemReferenceService
				.getReference(dao.getCi(new ConfigurationItemReference(configurationItemId, configurationItemType)));
	}
}
