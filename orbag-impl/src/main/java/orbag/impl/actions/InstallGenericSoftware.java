package orbag.impl.actions;

import org.springframework.stereotype.Component;

import orbag.action.ActionRequest;
import orbag.action.ActionUtils;
import orbag.action.ConfigurationItemActionBase;
import orbag.impl.cis.Server;
import orbag.metadata.DisplayLabel;

@Component
@DisplayLabel("Install Generic software")
public class InstallGenericSoftware extends ConfigurationItemActionBase{

	@Override
	public boolean isAvailableFor(ActionRequest request) {
		return ActionUtils.areAllObjectsOfType(request.getTargetCis(), Server.class);
	}


	@Override
	public String execute(ActionRequest request) {
		return "Software installed on "+request.getTargetCis().size()+" servers";
	}

}
