package orbag.server.view;

import orbag.reference.ConfigurationItemReference;

public class GetAvailableViewsRequest {

	ConfigurationItemReference targetCi;

	public ConfigurationItemReference getTargetCi() {
		return targetCi;
	}

	public void setTargetCi(ConfigurationItemReference targetCi) {
		this.targetCi = targetCi;
	}
}
