package orbag.samples.actions;

import orbag.metadata.DisplayLabelUtils;
import org.springframework.stereotype.Component;

import orbag.action.ActionConsequences;
import orbag.action.ActionRequest;
import orbag.action.ActionResult;
import orbag.action.ConfigurationItemActionBase;
import orbag.samples.brewery.Brewery;
import orbag.samples.brewery.PurchaseAction;
import orbag.samples.cis.BreweryMenuItem;
import orbag.metadata.DisplayLabel;
import orbag.visibility.ManagedClasses;

@Component
@DisplayLabel("Order %")
@ManagedClasses(BreweryMenuItem.class)
public class OrderFromMenu extends ConfigurationItemActionBase implements PurchaseAction {

	@Override
	public boolean isAvailableFor(ActionRequest request) {
		return request.hasSourceCi() && request.getSourceCi() instanceof Brewery;
	}

	@Override
	public void execute(ActionRequest request, ActionResult result) {
		result.setConsequences(ActionConsequences.NONE);
		result.setMessage(
				"You have ordered: "
						+ String.join(",",
								request.getTargetCis().stream().map(DisplayLabelUtils::getDisplayLabel)
										.toList())
						+ " on " + DisplayLabelUtils.getDisplayLabel( request.getSourceCi())+
				", price: "+getPrice(request)+" payment method: "+getPaymentMethod(request));
	}

	@Override
	public double getPrice(ActionRequest request) {
		return request.getTargetCis().size() * 5;
	}

}
