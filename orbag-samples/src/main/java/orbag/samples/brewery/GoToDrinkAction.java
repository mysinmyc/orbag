package orbag.samples.brewery;

import orbag.action.*;
import org.springframework.stereotype.Component;

import orbag.metadata.DisplayLabel;
import orbag.visibility.ManagedClasses;


@Component
@ManagedClasses(Brewery.class)
@DisplayLabel("Go to drink")
public class GoToDrinkAction extends ConfigurationItemActionBase{

	@Override
	public void execute(ActionRequest request, ActionResult result) throws ActionExecutionException {
		result.setConsequences(ActionConsequences.NONE);
		result.setMessage( " Oh yeah, "+((Brewery)request.getTargetCis().get(0)).getName()+" it's a good choice!!!");
	}

}
