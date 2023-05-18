package orbag.server.list;

import orbag.dao.PersistedBy;
import orbag.metadata.ConfigurationItem;

@ConfigurationItem
@PersistedBy(TestListRepository.class)
public class TestListCi implements orbag.metadata.Identifiable<Integer>, orbag.metadata.Displayable {

	Integer identifier;

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	

}

