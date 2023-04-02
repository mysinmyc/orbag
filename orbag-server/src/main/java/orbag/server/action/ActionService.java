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
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.Manageable;
import orbag.metadata.MetadataRegistry;
import orbag.reference.ConfigurationItemReference;
import orbag.security.AccessType;
import orbag.security.OrbagSecurityException;
import orbag.security.SecurityAssertionService;
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
	
	@Autowired
	SecurityAssertionService securityAssertionService;

	@Autowired
	MetadataRegistry metadataRegistry;
	
	boolean hasUseAccessToSourceCi(ConfigurationItemReference sourceCi,Authentication user) {
		if (sourceCi==null) {
			return true;
		}
		
		ConfigurationItemDescriptor sourceCiDescriptor = metadataRegistry.getConfigurationItemDescriptorByName(sourceCi.getConfigurationItemType());
		
		return securityAssertionService.hasAuthorizationToConfigurationItemDescriptor(sourceCiDescriptor, user, 
				AccessType.USE,AccessType.READ,AccessType.MODIFY);
	}
	
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
	
	public List<SerializableAction> getAvaiableActionsFor(ConfigurationItemReference sourceCiReference, List<ConfigurationItemReference> targetCisReferences, Authentication user) {	
		if (targetCisReferences == null || targetCisReferences.isEmpty() || !hasUseAccessToSourceCi(sourceCiReference, user)) {
			return new ArrayList<SerializableAction>();
		}
		List<?> targetCis = dao.getCis(targetCisReferences);
		ActionRequest request = new ActionRequest();
		request.setSourceCi(dao.getCi(sourceCiReference));
		request.setTargetCis(targetCis);
		List<ConfigurationItemAction> availableActions = actionRegistry.getAllActions();	
		return availableActions.stream().filter(a -> isActionVisibile(a, targetCis,user)).filter(a -> a.isAvailableFor(
				request)) .map( a -> new SerializableAction(a.getIdentifier(),formatLabel(a.getDisplayLabel(),targetCisReferences))).toList();
	}
	
	
	ConfigurationItemAction getActionFromid(String id) {
		return	actionRegistry.getAllActions().stream().filter(a -> a.getIdentifier().equals(id)).findAny().orElse(null);
	}
	
	public ActionResult submit(ConfigurationItemReference sourceCiReference, SerializableAction serializableAction, List<ConfigurationItemReference> targetCisReferences, Authentication user) throws OrbagSecurityException {
		ConfigurationItemAction action = getActionFromid(serializableAction.getIdentifier());
		if (action==null) {
			throw new OrbagServerException("Invalid action "+serializableAction.getIdentifier());
		}
		if (!hasUseAccessToSourceCi(sourceCiReference,user)) {
			throw new OrbagSecurityException(user, sourceCiReference, AccessType.USE);
		}
		Object sourceCi = dao.getCi(sourceCiReference);
		List<?> targetCis = dao.getCis(targetCisReferences);
		ActionRequest request = new ActionRequest();
		request.setSourceCi(sourceCi);
		request.setTargetCis(targetCis);
		if ( ! (isActionVisibile(action, targetCis, user) && action.isAvailableFor(request))) {
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
