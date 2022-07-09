package orbag.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionRegistry {

	@Autowired(required =false)
	List<ConfigurationItemAction> actions;
	
	public List<ConfigurationItemAction> getAllActions() {
		return actions;
	}
}
