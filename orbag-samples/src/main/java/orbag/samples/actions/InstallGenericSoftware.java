package orbag.samples.actions;

import orbag.action.*;
import orbag.dao.ConfigurationItemDao;
import orbag.dao.ConfigurationItemNotFoundException;
import orbag.input.FieldGroupBuilder;
import orbag.metadata.MetadataRegistry;
import orbag.metadata.UnmanagedObjectException;
import orbag.reference.ConfigurationItemReference;
import orbag.samples.cis.ApplicationInstance;
import orbag.samples.cis.BusinessApplication;
import orbag.security.AccessRestrictedByAccessType;
import orbag.security.AccessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.samples.cis.Server;
import orbag.metadata.DisplayLabel;
import orbag.security.AccessRestricted;
import orbag.visibility.ManagedClasses;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
@ManagedClasses(Server.class)
@DisplayLabel("Install Generic software on %")
@AccessRestrictedByAccessType(AccessType.MODIFY)
public class InstallGenericSoftware extends ConfigurationItemActionBase{

	@Autowired
	MetadataRegistry metadataRegistry;

	@Autowired
	ConfigurationItemDao dao;

	@Override
	public void buildParametersFor(ActionRequest request, FieldGroupBuilder builder) {
		builder.addStringField("installation_path","Installation path");
		try {
			builder.addReferenceField("business_application","Application to install", metadataRegistry.getConfigurationItemDescriptorByClass(BusinessApplication.class).getName());
		} catch (UnmanagedObjectException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean validateRequest(ActionRequest request, ActionFeedback feedback) {
		Stream.of("business_application","installation_path").filter(Predicate.not(request.getParameters()::isFilled)).forEach(
				f->
				feedback.addValidationError("missing "+f,  f)
		);
		return  feedback.isOperationValid();
	}

	@Override
	public void execute(ActionRequest request, ActionResult result) throws ActionExecutionException {

		try {
			BusinessApplication businessApplication = (BusinessApplication) dao.getExistingCiOrThrow((ConfigurationItemReference) request.getParameters().getField("business_application").getValue());


			for (Object serverObject: request.getTargetCis()) {

				ApplicationInstance currentInstance = new ApplicationInstance();
				currentInstance.setInstallationPath(request.getParameters().getValue("installation_path"));
				currentInstance.setBusinessApplication(businessApplication);
				currentInstance.setServer((Server)serverObject);

				dao.create(currentInstance);
			}
		} catch (Exception e) {
			throw new ActionExecutionException(e);
		}


		result.setMessage("Software istalled on "+ request.getTargetCis().size()+" servers");
		result.setConsequences(ActionConsequences.UPDATED);
	}

}
