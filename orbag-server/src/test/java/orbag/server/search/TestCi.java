package orbag.server.search;

import java.util.HashMap;
import java.util.Map;

import orbag.dao.PersistedBy;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.Manageable;
import orbag.search.Searcheable;

@ConfigurationItem
@PersistedBy(TestRepository.class)
public class TestCi implements Manageable<String> {

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
	
	
	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	Map<String,String> data = new HashMap<>();
	
}

