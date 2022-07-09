package orbag.server.list;

import java.util.List;

import orbag.reference.ConfigurationItemReference;

public class ListConfigurationItemResponse {

	List<ConfigurationItemReference> cis;

	public List<ConfigurationItemReference> getCis() {
		return cis;
	}

	public void setCis(List<ConfigurationItemReference> cis) {
		this.cis = cis;
	}
}
