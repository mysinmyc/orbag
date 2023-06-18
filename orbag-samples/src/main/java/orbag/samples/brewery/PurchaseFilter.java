package orbag.samples.brewery;

import orbag.action.*;
import org.springframework.stereotype.Component;

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
	public boolean filterAction(ConfigurationItemAction action,ActionRequest request, ActionFeedback feedback) {
		return ((PurchaseAction)action).validatePayment(request, feedback);
	}

}
