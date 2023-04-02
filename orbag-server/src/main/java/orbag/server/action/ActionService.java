package orbag.server.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import orbag.action.ActionRegistry;
import orbag.action.ActionRequest;
import orbag.action.ActionResult;
import orbag.action.ConfigurationItemAction;
import orbag.dao.ConfigurationItemDao;
import orbag.metadata.Manageable;
import orbag.reference.ConfigurationItemReference;
import orbag.server.OrbagServerException;
import orbag.visibility.FilterContext;
import orbag.visibility.VisibilityManager;

@Component
public class ActionService {

	@Autowired
	VisibilityManager visibilityManager;
	
	@Autowired
	ActionRegistry actionRegistry;
	
	@Autowired
	ConfigurationItemDao dao;
	
	
	boolean isActionVisibile(ConfigurationItemAction action, List<?> configurationItems, Authentication user) {
		for (Object ci:  configurationItems) {
			if (!visibilityManager.isObjectVisibile(action, FilterContext.forTargetObject(ci).forUser(user))) {
				return false;
			}
		}
		return true;
	}
	
	protected String formatLabel(String label, List<ConfigurationItemReference> cisReferences ) {
		if (label.indexOf("%")==-1) {
			return label;
		}
		if (cisReferences.size()>1) {
			return label.replace("%", cisReferences.size()+ " cis");
		}		
		String ciLabel = ((Manageable<?>)dao.getCi(cisReferences.get(0))).getDisplayLabel();
		return label.replace("%", ciLabel);

	}
	
	public List<SerializableAction> getAvaiableActionsFor(List<ConfigurationItemReference> cisReferences, Authentication user) {	
		if (cisReferences == null || cisReferences.isEmpty()) {
			return new ArrayList<SerializableAction>();
		}
		List<?> cis = dao.getCis(cisReferences);
		ActionRequest request = new ActionRequest();
		request.setTargetCis(cis);
		List<ConfigurationItemAction> availableActions = actionRegistry.getAllActions();	
		return availableActions.stream().filter(a -> isActionVisibile(a, cis,user)).filter(a -> a.isAvailableFor(
				request)) .map( a -> new SerializableAction(a.getIdentifier(),formatLabel(a.getDisplayLabel(),cisReferences))).toList();
	}
	
	
	ConfigurationItemAction getActionFromid(String id) {
		return	actionRegistry.getAllActions().stream().filter(a -> a.getIdentifier().equals(id)).findAny().orElse(null);
	}
	
	public ActionResult submit(SerializableAction serializableAction, List<ConfigurationItemReference> cisReferences, Authentication user) {
		ConfigurationItemAction action = getActionFromid(serializableAction.getIdentifier());
		if (action==null) {
			throw new OrbagServerException("Invalid action "+serializableAction.getIdentifier());
		}
		List<?> cis = dao.getCis(cisReferences);
		ActionRequest request = new ActionRequest();
		request.setTargetCis(cis);
		if ( ! (isActionVisibile(action, cis, user) && action.isAvailableFor(request))) {
			throw new OrbagServerException("Action not available");
		}		
		ActionResult result= action.execute(request);
		if (result==null) {
			return new ActionResult();
		} else {
			return result;
		}
	}
}
