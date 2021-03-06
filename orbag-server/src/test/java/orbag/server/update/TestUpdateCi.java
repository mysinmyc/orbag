package orbag.server.update;

import orbag.dao.PersistedBy;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;
import orbag.metadata.Manageable;

@ConfigurationItem(identifierClass = String.class)
@PersistedBy(TestUpdateRepository.class)
public class TestUpdateCi implements Manageable<String> {

	String identifier;

	String stringProperty;
	
	Integer integerProperty;
	
	Boolean booleanProperty;
	
	String readOnlyProperty;
	
	public String getIdentifier() {
		return identifier;
	}

	@ConfigurationItemProperty
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	
	public String getStringProperty() {
		return stringProperty;
	}

	@ConfigurationItemProperty
	public void setStringProperty(String stringProperty) {
		this.stringProperty = stringProperty;
	}

	public Integer getIntegerProperty() {
		return integerProperty;
	}

	@ConfigurationItemProperty
	public void setIntegerProperty(Integer integerProperty) {
		this.integerProperty = integerProperty;
	}

	public Boolean getBooleanProperty() {
		return booleanProperty;
	}

	@ConfigurationItemProperty
	public void setBooleanProperty(Boolean booleanProperty) {
		this.booleanProperty = booleanProperty;
	}

	public String getReadOnlyProperty() {
		return readOnlyProperty;
	}

	@ConfigurationItemProperty(readOnly = true)
	public void setReadOnlyProperty(String readOnlyProperty) {
		this.readOnlyProperty = readOnlyProperty;
	}


}

