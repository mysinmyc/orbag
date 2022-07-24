package orbag.server.list;

import orbag.dao.PersistedBy;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.Manageable;

@ConfigurationItem
@PersistedBy(TestListRepository.class)
public class TestListCi implements Manageable<Integer> {

	Integer identifier;

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	

}

