package orbag.impl.brewery;

import org.springframework.stereotype.Component;

import orbag.action.ActionRequest;
import orbag.action.ActionResult;
import orbag.action.ConfigurationItemAction;
import orbag.action.ConfigurationItemActionExecutionFilter;
import orbag.input.FieldGroupBuilder;
import orbag.visibility.ManagedClasses;

@Component
@ManagedClasses(Object.class)
public class PurchaseFilter implements ConfigurationItemActionExecutionFilter{

	@Override
	public boolean isAvailableFor(ConfigurationItemAction action, ActionRequest request) {
		return action instanceof PurchaseAction;
	}

	@Override
	public void setActionParameters(ConfigurationItemAction action, FieldGroupBuilder parametersBuilder, ActionRequest request) {
		parametersBuilder.addEnumField("_paymentMethod","PaymentMethod",PaymentMethod.class);
	}

	@Override
	public boolean filterAction(ConfigurationItemAction action,ActionRequest request, ActionResult result) {
		double price=((PurchaseAction)action).getPrice(request);
		PaymentMethod paymentMethod = ((PurchaseAction)action).getPaymentMethod(request);
		if (price > 10d) {
			result.setMessage("Payment of "+price+" via "+paymentMethod+" denied");
			return false;
		}
		return true;
	}

}
