package orbag.impl.actions;

import org.springframework.stereotype.Component;

import orbag.action.ActionConsequences;
import orbag.action.ActionRequest;
import orbag.action.ActionResult;
import orbag.action.ConfigurationItemActionBase;
import orbag.impl.brewery.Brewery;
import orbag.impl.cis.BreweryMenuItem;
import orbag.metadata.DisplayLabel;
import orbag.metadata.Manageable;
import orbag.visibility.ManagedClasses;

@Component
@DisplayLabel("Order %")
@ManagedClasses(BreweryMenuItem.class)
public class OrderFromMenu extends ConfigurationItemActionBase {

	@Override
	public boolean isAvailableFor(ActionRequest request) {
		return request.hasSourceCi() && request.getSourceCi() instanceof Brewery;
	}

	@Override
	public ActionResult execute(ActionRequest request) {

		ActionResult result = new ActionResult();
		result.setConsequences(ActionConsequences.NONE);
		result.setMessage(
				"You have ordered: "
						+ String.join(",",
								request.getTargetCis().stream().map(i -> ((Manageable<?>) i).getDisplayLabel())
										.toList())
						+ " on " + ((Manageable<?>) request.getSourceCi()).getDisplayLabel());
		return result;
	}

}
