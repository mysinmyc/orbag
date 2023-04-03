package orbag.impl.brewery;

import orbag.action.ActionRequest;
import orbag.input.EnumField;

public interface PurchaseAction {

	
	default PaymentMethod getPaymentMethod(ActionRequest request) {
		return  PaymentMethod.valueOf(((EnumField)request.getParameters().getField("_paymentMethod")).getValue());
	}
	
	double getPrice(ActionRequest request);

	
}
