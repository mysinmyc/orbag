package orbag.impl.brewery;

import org.springframework.stereotype.Component;

import orbag.action.ActionRequest;
import orbag.action.ActionUtils;
import orbag.action.ConfigurationItemActionBase;
import orbag.metadata.DisplayLabel;

@Component
@DisplayLabel("Go to drink")
public class GoToDrinkAction extends ConfigurationItemActionBase{

	@Override
	public boolean isAvailableFor(ActionRequest request) {
		return ActionUtils.isSingleObjectOfType(request.getTargetCis(), Brewery.class);
	}

	@Override
	public String execute(ActionRequest request) {
		return " Oh yeah, "+((Brewery)request.getTargetCis().get(0)).getName()+" it's a good choice!!!" ;
	}

}
