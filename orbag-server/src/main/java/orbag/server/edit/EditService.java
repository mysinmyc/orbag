package orbag.server.edit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.reference.ConfigurationItemReference;
import orbag.server.metadata.MetadataService;

@Component
public class EditService {

	@Autowired
	MetadataService metadataService;
	
	@Autowired
	ConfigurationItemDao dao;
	
	public Object getConfiguratioItem(String configurationItemType, String ConfigurationItemId) {
		return dao.getCi(new ConfigurationItemReference(ConfigurationItemId, configurationItemType));
	}

	public Map<String, Object> getProperties(Object configurationItem) {
		Map<String, Object> result = new HashMap<>();
		
		
		return result;
	}
}
