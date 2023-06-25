package orbag.action;

import orbag.input.FieldGroupBuilder;
import orbag.metadata.Describable;
import orbag.metadata.Displayable;

public interface ConfigurationItemAction extends Displayable, Describable {


	default boolean isQuick() {
		return false;
	}

	String getIdentifier();

	default boolean isAvailableFor(ActionRequest request) {
		return true;
	}

	default void buildParametersFor(ActionRequest request, FieldGroupBuilder builder) {
		
	}

	default boolean validateRequest(ActionRequest request, ActionFeedback feedback) {
		return true;
	}

	void execute(ActionRequest request, ActionResult result) throws ActionExecutionException;

}
