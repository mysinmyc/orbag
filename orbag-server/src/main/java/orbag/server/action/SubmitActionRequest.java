package orbag.server.action;

import java.util.List;

import orbag.reference.ConfigurationItemReference;

public class SubmitActionRequest {
	
	SerializableAction action;
	
	
	public SerializableAction getAction() {
		return action;
	}

	public void setAction(SerializableAction action) {
		this.action = action;
	}

	List<ConfigurationItemReference> targetCis;

	public List<ConfigurationItemReference> getTargetCis() {
		return targetCis;
	}

	public void setTargetCis(List<ConfigurationItemReference> targetCis) {
		this.targetCis = targetCis;
	}
}
