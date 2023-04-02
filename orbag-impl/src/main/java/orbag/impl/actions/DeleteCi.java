package orbag.impl.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.action.ActionConsequences;
import orbag.action.ActionRequest;
import orbag.action.ActionResult;
import orbag.action.ConfigurationItemActionBase;
import orbag.dao.ConfigurationItemDao;
import orbag.metadata.DisplayLabel;
import orbag.metadata.MetadataRegistry;
import orbag.security.AccessRestrictedByAccessType;
import orbag.security.AccessType;
import orbag.visibility.ManagedClasses;

@Component
@DisplayLabel("Delete %")
@ManagedClasses(Object.class)
@AccessRestrictedByAccessType(AccessType.DELETE)
public class DeleteCi extends ConfigurationItemActionBase {

	@Autowired
	ConfigurationItemDao dao;

	@Autowired
	MetadataRegistry metadataRegistry;

	@Override
	public boolean isAvailableFor(ActionRequest request) {
		return request.getTargetCis().size() == 1 && dao.isWritable(request.getTargetCis().get(0)) && !metadataRegistry
				.getConfigurationItemDescriptorByClass(request.getTargetCis().get(0).getClass()).isReadOnly();
	}

	@Override
	public ActionResult execute(ActionRequest request) {

		for (Object ci : request.getTargetCis()) {
			dao.delete(ci);
		}

		ActionResult result = new ActionResult();
		result.setMessage(request.getTargetCis().size() + " configuration items successfully deleted");
		result.setConsequences(ActionConsequences.DELETED);
		return result;
	}

}
