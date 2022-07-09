package orbag.server.action;

import java.util.List;

public class GetAvailableActionsResponse {

	List<SerializableAction> availableActions;

	public List<SerializableAction> getAvailableActions() {
		return availableActions;
	}

	public void setAvailableActions(List<SerializableAction> availableActions) {
		this.availableActions = availableActions;
	}
	
}
