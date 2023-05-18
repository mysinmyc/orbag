package orbag.impl.actions;

import orbag.metadata.UnmanagedObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.action.ActionConsequences;
import orbag.action.ActionRequest;
import orbag.action.ActionResult;
import orbag.action.ConfigurationItemActionBase;
import orbag.action.ConfirmationAwareFilter;
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
public class DeleteCi extends ConfigurationItemActionBase implements ConfirmationAwareFilter {

	@Autowired
	ConfigurationItemDao dao;

	@Autowired
	MetadataRegistry metadataRegistry;

	@Override
	public boolean isAvailableFor(ActionRequest request) {
		try {
			return request.getTargetCis().size() == 1 && dao.isWritable(request.getTargetCis().get(0)) && !metadataRegistry
					.getConfigurationItemDescriptorByClass(request.getTargetCis().get(0).getClass()).isReadOnly();
		} catch (UnmanagedObjectException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void execute(ActionRequest request, ActionResult result) {
		for (Object ci : request.getTargetCis()) {
			dao.delete(ci);
		}
		result.setMessage(request.getTargetCis().size() + " configuration items successfully deleted");
		result.setConsequences(ActionConsequences.DELETED);
	}

}
