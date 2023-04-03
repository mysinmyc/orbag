package orbag.server.action;

import java.util.List;

import orbag.reference.ConfigurationItemReference;

public abstract class ActionInputBase {

	ConfigurationItemReference sourceCi;
	
	public ConfigurationItemReference getSourceCi() {
		return sourceCi;
	}

	public void setSourceCi(ConfigurationItemReference sourceCi) {
		this.sourceCi = sourceCi;
	}

	List<ConfigurationItemReference> targetCis;

	public List<ConfigurationItemReference> getTargetCis() {
		return targetCis;
	}

	public void setTargetCis(List<ConfigurationItemReference> targetCis) {
		this.targetCis = targetCis;
	}
}
