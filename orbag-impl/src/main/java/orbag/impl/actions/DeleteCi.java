package orbag.impl.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.action.ActionRequest;
import orbag.action.ConfigurationItemActionBase;
import orbag.dao.Dao;
import orbag.metadata.DisplayLabel;

@Component
@DisplayLabel("Delete")
public class DeleteCi extends ConfigurationItemActionBase{

	@Autowired
	Dao dao;
	
	
	@Override
	public boolean isAvailableFor(ActionRequest request) {
		return request.getTargetCis().size() == 1;
	}

	@Override
	public String execute(ActionRequest request) {

		for (Object ci : request.getTargetCis()) {
			dao.delete(ci);
		}
	
		return request.getTargetCis().size() + " configuration items successfully deleted";
	}

}
