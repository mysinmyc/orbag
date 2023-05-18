package orbag.server.action;

import orbag.dao.PersistedBy;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;

@ConfigurationItem(identifierClass = String.class)
@PersistedBy(TestActionRepository.class)
public class TestActionCi implements orbag.metadata.Identifiable<String>, orbag.metadata.Displayable {

	String identifier;

	public String getIdentifier() {
		return identifier;
	}

	@ConfigurationItemProperty(mandatoryForCreation = true)
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	@Override
	public String getDisplayLabel() {
		return "label "+identifier;
	}

}

