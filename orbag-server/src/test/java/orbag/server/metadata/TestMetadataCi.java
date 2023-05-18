package orbag.server.metadata;

import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;

@ConfigurationItem
public class TestMetadataCi implements orbag.metadata.Identifiable<Integer>, orbag.metadata.Displayable {

	Integer identifier;

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	
	
	String stringProperty;

	public String getStringProperty() {
		return stringProperty;
	}

	@ConfigurationItemProperty
	public void setStringProperty(String stringProperty) {
		this.stringProperty = stringProperty;
	}
	

}

