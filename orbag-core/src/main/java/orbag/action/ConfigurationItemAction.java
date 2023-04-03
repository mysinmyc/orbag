package orbag.action;

import orbag.metadata.Displayable;

public interface ConfigurationItemAction extends Displayable {

	String getIdentifier();

	default boolean isAvailableFor(ActionRequest request) {
		return true;
	}

	default void buildParametersFor(ActionRequest request) {
		
	}
	
	void execute(ActionRequest request, ActionResult result);

}
