package orbag.server.search;

import orbag.metadata.ConfigurationItem;
import orbag.metadata.Manageable;
import orbag.search.Searcheable;

@ConfigurationItem
public class TestCiCustomSearchExector implements Manageable<String> {

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

