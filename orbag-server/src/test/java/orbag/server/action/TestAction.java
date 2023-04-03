package orbag.server.action;

import org.springframework.stereotype.Component;

import orbag.action.ActionConsequences;
import orbag.action.ActionRequest;
import orbag.action.ActionResult;
import orbag.action.ConfigurationItemActionBase;
import orbag.metadata.DisplayLabel;
import orbag.visibility.ManagedClasses;

@Component
@ManagedClasses(TestActionCi.class)
@DisplayLabel("Action on %")
public class TestAction extends ConfigurationItemActionBase{

	
	@Override
	public void execute(ActionRequest request, ActionResult result) {
		result.setConsequences(ActionConsequences.UPDATED);
	}

}
