package orbag.action;

import orbag.input.FieldGroupBuilder;

public interface ConfigurationItemActionExecutionFilter {

	public boolean isAvailableFor(ConfigurationItemAction action,ActionRequest request);
	
	void setActionParameters(ConfigurationItemAction action,FieldGroupBuilder parametersBuilder, ActionRequest request);
	
	boolean filterAction(ConfigurationItemAction action,ActionRequest request, ActionResult result);
}
