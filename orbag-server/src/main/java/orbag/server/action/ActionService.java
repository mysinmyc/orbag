package orbag.server.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.action.ActionRegistry;
import orbag.action.ActionRequest;
import orbag.action.ConfigurationItemAction;
import orbag.dao.Dao;
import orbag.reference.ConfigurationItemReference;
import orbag.server.OrbagServerException;

@Component
public class ActionService {

	@Autowired
	ActionRegistry actionRegistry;
	
	@Autowired
	Dao dao;
	
	public List<SerializableAction> getAvaiableActionsFor(List<ConfigurationItemReference> cisReferences) {	
		if (cisReferences == null || cisReferences.isEmpty()) {
			return new ArrayList<SerializableAction>();
		}
		List<?> cis = dao.getCis(cisReferences);
		ActionRequest request = new ActionRequest();
		request.setTargetCis(cis);
		List<ConfigurationItemAction> availableActions = new ArrayList<>();
		for (ConfigurationItemAction action : actionRegistry.getAllActions()) {
			if (action.isAvailableFor(request)) {
				availableActions.add(action);
			}
		}
		return availableActions.stream().map( a -> new SerializableAction(a.getIdentifier(),a.getDisplayLabel())).toList();
	}
	
	
	ConfigurationItemAction getActionFromid(String id) {
		return	actionRegistry.getAllActions().stream().filter(a -> a.getIdentifier().equals(id)).findAny().orElse(null);
	}
	
	public String submit(SerializableAction serializableAction, List<ConfigurationItemReference> cisReferences) {
		ConfigurationItemAction action = getActionFromid(serializableAction.getIdentifier());
		if (action==null) {
			throw new OrbagServerException("Invalid action "+serializableAction.getIdentifier());
		}
		List<?> cis = dao.getCis(cisReferences);
		ActionRequest request = new ActionRequest();
		request.setTargetCis(cis);
		if (!action.isAvailableFor(request)) {
			throw new OrbagServerException("Action not available");
		}		
		return action.execute(request);
	}
}
