package orbag.server.graph;

import orbag.reference.ConfigurationItemReference;

import java.util.List;

public class GetAvailablePathsRequest {

	ConfigurationItemReference startingCi;

	public ConfigurationItemReference getStartingCi() {
		return startingCi;
	}

	public void setStartingCi(ConfigurationItemReference startingCi) {
		this.startingCi = startingCi;
	}
}
