package orbag.action;

import orbag.metadata.Displayable;

public interface ConfigurationItemAction extends Displayable{

	String getIdentifier();
	
	boolean isAvailableFor(ActionRequest request);
	
	String execute(ActionRequest request);
	
}
