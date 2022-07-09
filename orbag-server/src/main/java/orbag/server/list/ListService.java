package orbag.server.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.dao.Dao;
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.Manageable;
import orbag.metadata.MetadataRegistry;
import orbag.reference.ConfigurationItemReference;
import orbag.server.OrbagServerException;

@Component
public class ListService {

	@Autowired
	MetadataRegistry metadataRegistry;

	@Autowired
	Dao dao;

	List<ConfigurationItemReference> list(String configurationItemName) {

		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry
				.getConfigurationItemDescriptorByName(configurationItemName);

		if (configurationItemDescriptor == null) {
			throw new OrbagServerException(configurationItemName + " non managed");
		}

		return dao.list(configurationItemDescriptor.getJavaClass()).stream().filter(ci -> ci instanceof Manageable).map(
				ci -> ConfigurationItemReference.fromConfigurationItem(configurationItemDescriptor, (Manageable) ci)).toList();
	}
}
