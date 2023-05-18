package orbag.server.view;

import orbag.dao.PersistedBy;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;

@ConfigurationItem(identifierClass = String.class)
@PersistedBy(TestViewRepository.class)
public class TestViewCi implements orbag.metadata.Identifiable<String>, orbag.metadata.Displayable {

	String identifier;

	public String getIdentifier() {
		return identifier;
	}

	@ConfigurationItemProperty(mandatoryForCreation = true)
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	

}

