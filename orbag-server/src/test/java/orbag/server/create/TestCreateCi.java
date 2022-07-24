package orbag.server.create;

import orbag.dao.PersistedBy;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;
import orbag.metadata.Manageable;

@ConfigurationItem
@PersistedBy(TestCreateRepository.class)
public class TestCreateCi implements Manageable<String> {

	String identifier;

	public String getIdentifier() {
		return identifier;
	}

	@ConfigurationItemProperty(mandatoryForCreation = true)
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	

}

