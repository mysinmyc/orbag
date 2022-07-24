package orbag.server.action;

import org.springframework.stereotype.Component;

import orbag.action.ActionRequest;
import orbag.action.ActionUtils;
import orbag.action.ConfigurationItemActionBase;

@Component
public class TestAction extends ConfigurationItemActionBase{

	@Override
	public boolean isAvailableFor(ActionRequest request) {
		return ActionUtils.areAllObjectsOfType(request.getTargetCis(), TestActionCi.class);
	}

	@Override
	public String execute(ActionRequest request) {
		return null;
	}

}
