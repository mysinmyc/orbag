package orbag.server.action;

import orbag.action.*;
import org.springframework.stereotype.Component;

import orbag.metadata.DisplayLabel;
import orbag.visibility.ManagedClasses;

@Component
@ManagedClasses(TestActionCi.class)
@DisplayLabel("Action on %")
public class TestAction extends ConfigurationItemActionBase{

	
	@Override
	public void execute(ActionRequest request, ActionResult result) throws ActionExecutionException {
		result.setConsequences(ActionConsequences.UPDATED);
	}

}
