package orbag.server.search;

import orbag.metadata.ConfigurationItem;
import orbag.search.Searcheable;

@ConfigurationItem
public class TestCiCustomSearchExector implements orbag.metadata.Identifiable<String>, orbag.metadata.Displayable {

	String identifier;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	@Searcheable
	String stringSearcheableField;
	
	@Searcheable
	Integer numericSearcheableField;

	
	@Searcheable
	Boolean booleanSearcheableField;
}

