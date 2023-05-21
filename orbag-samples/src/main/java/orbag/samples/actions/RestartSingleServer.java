package orbag.samples.actions;

import orbag.action.*;
import org.springframework.stereotype.Component;

import orbag.samples.cis.Server;
import orbag.metadata.DisplayLabel;
import orbag.visibility.ManagedClasses;

@Component
@ManagedClasses(Server.class)
@DisplayLabel("Restart single server")
public class RestartSingleServer extends ConfigurationItemActionBase implements ConfirmationAwareFilter {

	@Override
	public boolean isAvailableFor(ActionRequest request) {
		return request.getTargetCis().size() ==1 ;
	}
	
	@Override
	public void execute(ActionRequest request,ActionResult result) {		
		result.setMessage("Submitted restart of "+ request.getTargetCis().size()+" servers");
		result.setConsequences(ActionConsequences.UNDEFINED);
	}

}
