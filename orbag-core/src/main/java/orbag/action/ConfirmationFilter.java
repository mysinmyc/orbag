package orbag.action;

import org.springframework.stereotype.Component;

import orbag.input.BooleanField;
import orbag.input.FieldGroupBuilder;
import orbag.visibility.ManagedClasses;


@Component
@ManagedClasses(Object.class)
public class ConfirmationFilter implements ConfigurationItemActionExecutionFilter{

	@Override
	public boolean isAvailableFor(ConfigurationItemAction action, ActionRequest request) {
		return action instanceof ConfirmationAwareFilter && ((ConfirmationAwareFilter)action).requireConfirmation(request);
	}

	@Override
	public void setActionParameters(ConfigurationItemAction action, FieldGroupBuilder parametersBuilder, ActionRequest request) {
		parametersBuilder.addBooleanField("_confirm","Confirm action?");
	}

	@Override
	public boolean filterAction(ConfigurationItemAction action, ActionRequest request, ActionFeedback feedback) {
		Boolean confirmed = ((BooleanField)request.getParameters().getField("_confirm")).getValue();
		if (! Boolean.TRUE.equals(confirmed)) {
			feedback.addValidationError("Action not confirmed");
			return false;
		}
		return true;
	}

}
