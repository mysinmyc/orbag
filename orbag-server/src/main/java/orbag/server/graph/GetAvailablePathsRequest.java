package orbag.server.graph;

import orbag.reference.ConfigurationItemReference;

import java.util.List;

public class GetAvailablePathsRequest {

	public List<ConfigurationItemReference> getRootCis() {
		return rootCis;
	}

	public void setRootCis(List<ConfigurationItemReference> rootCis) {
		this.rootCis = rootCis;
	}

	List<ConfigurationItemReference> rootCis;
}
