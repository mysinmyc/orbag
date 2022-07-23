package orbag.server.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.data.PaginationInfo;
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.Manageable;
import orbag.metadata.MetadataRegistry;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceExt;
import orbag.server.OrbagServerException;
import orbag.util.LimitedSizeList;

@Component
public class ListService {

	@Autowired
	MetadataRegistry metadataRegistry;

	@Autowired
	ConfigurationItemDao dao;

	List<ConfigurationItemReference> list(String configurationItemName, PaginationInfo paginationInfo) {

		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry
				.getConfigurationItemDescriptorByName(configurationItemName);

		if (configurationItemDescriptor == null) {
			throw new OrbagServerException(configurationItemName + " not managed");
		}
		

		LimitedSizeList<ConfigurationItemReference> cis = new LimitedSizeList<>();
		cis.setLimit(paginationInfo.getSize());
		dao.listInto(configurationItemDescriptor.getJavaClass(), ci -> {
			if (ci instanceof Manageable<?>) {
				ConfigurationItemReferenceExt reference = ConfigurationItemReferenceExt
						.fromConfigurationItem(configurationItemDescriptor, (Manageable<?>) ci);
				if (reference != null) {
					cis.add(reference);
				}
			}
		}, paginationInfo);

		return cis;
	}
}
