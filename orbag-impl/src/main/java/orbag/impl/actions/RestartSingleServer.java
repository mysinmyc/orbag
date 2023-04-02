package orbag.impl.actions;

import org.springframework.stereotype.Component;

import orbag.action.ActionConsequences;
import orbag.action.ActionRequest;
import orbag.action.ActionResult;
import orbag.action.ConfigurationItemActionBase;
import orbag.impl.cis.Server;
import orbag.metadata.DisplayLabel;
import orbag.visibility.ManagedClasses;

@Component
@ManagedClasses(Server.class)
@DisplayLabel("Restart single server")
public class RestartSingleServer extends ConfigurationItemActionBase {

	@Override
	public boolean isAvailableFor(ActionRequest request) {
		return request.getTargetCis().size() ==1 ;
	}
	
	@Override
	public ActionResult execute(ActionRequest request) {
		
		ActionResult result= new ActionResult();
		result.setMessage("Submitted restart of "+ request.getTargetCis().size()+" servers");
		result.setConsequences(ActionConsequences.UNDEFINED);
		return result;

		
	}

}
