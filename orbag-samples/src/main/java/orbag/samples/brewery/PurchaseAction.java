package orbag.samples.brewery;

import orbag.action.ActionRequest;
import orbag.action.ActionResult;
import orbag.input.EnumField;

public interface PurchaseAction {

	
	default PaymentMethod getPaymentMethod(ActionRequest request) {
		return  PaymentMethod.valueOf(((EnumField)request.getParameters().getField("_paymentMethod")).getValue());
	}
	
	double getPrice(ActionRequest request);
	
	
	default boolean validatePayment (ActionRequest request,  ActionResult actionResult) {
		double price =getPrice(request); 
		if (request.getParameters().getField("_paymentMethod").isEmpty()) {
			actionResult.addValidationError("Missing payment method","_paymentMethod");
			return false;
		}
		if (price > 10d) {
			actionResult.addValidationError("Payment of "+price+" via "+getPaymentMethod(request)+" denied");
			return false;
		}
		
		return true;
	}
	
}
