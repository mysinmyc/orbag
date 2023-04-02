package orbag.server.view;

import orbag.dao.PersistedBy;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;
import orbag.metadata.Manageable;

@ConfigurationItem(identifierClass = String.class)
@PersistedBy(TestViewRepository.class)
public class TestViewCi implements Manageable<String> {

	String identifier;

	public String getIdentifier() {
		return identifier;
	}

	@ConfigurationItemProperty(mandatoryForCreation = true)
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	

}

