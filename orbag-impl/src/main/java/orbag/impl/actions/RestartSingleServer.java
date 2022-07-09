package orbag.impl.actions;

import org.springframework.stereotype.Component;

import orbag.action.ActionRequest;
import orbag.action.ActionUtils;
import orbag.action.ConfigurationItemActionBase;
import orbag.impl.cis.Server;
import orbag.metadata.DisplayLabel;

@Component
@DisplayLabel("Restart single server")
public class RestartSingleServer extends ConfigurationItemActionBase {

	@Override
	public boolean isAvailableFor(ActionRequest request) {
		return request.getTargetCis().size() == 1
				&& ActionUtils.areAllObjectsOfType(request.getTargetCis(), Server.class);
	}

	@Override
	public String execute(ActionRequest request) {
		return "Submitted restart of "+ ((Server)request.getTargetCis().get(0)).getName();
	}

}
