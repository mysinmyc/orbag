package orbag.samples.actions;

import orbag.action.*;
import org.springframework.stereotype.Component;

import orbag.samples.cis.Server;
import orbag.metadata.DisplayLabel;
import orbag.security.AccessRestricted;
import orbag.visibility.ManagedClasses;

@Component
@ManagedClasses(Server.class)
@DisplayLabel("Install Generic software on %")
@AccessRestricted
public class InstallGenericSoftware extends ConfigurationItemActionBase{

	@Override
	public void execute(ActionRequest request, ActionResult result) throws ActionExecutionException {
		result.setMessage("Software istalled on "+ request.getTargetCis().size()+" servers");
		result.setConsequences(ActionConsequences.UNDEFINED);
	}

}
