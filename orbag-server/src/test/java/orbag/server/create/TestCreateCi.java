package orbag.server.create;

import orbag.dao.PersistedBy;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;

@ConfigurationItem
@PersistedBy(TestCreateRepository.class)
public class TestCreateCi implements orbag.metadata.Identifiable<String>, orbag.metadata.Displayable {

	String identifier;

	public String getIdentifier() {
		return identifier;
	}

	@ConfigurationItemProperty(mandatoryForCreation = true)
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	

}

