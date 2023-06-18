package orbag.server.create;

import orbag.create.*;
import orbag.metadata.UnmanagedObjectException;
import orbag.reference.ConfigurationItemReference;
import orbag.util.UnsafeBiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import orbag.input.SerializableFieldGroup;
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataRegistry;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.security.AccessType;
import orbag.security.OrbagSecurityException;
import orbag.security.SecurityAssertionService;
import orbag.util.ObjectHolder;
import orbag.visibility.FilterContext;
import orbag.visibility.VisibilityManager;

@Component
public class CreateService {

	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;

	@Autowired
	MetadataRegistry metadataRegistry;

	@Autowired
	ConfigurationItemCreationWizardRegistry configurationItemCreationWizardRegistry;

	@Autowired
	VisibilityManager visibilityManager;

	@Autowired
	SecurityAssertionService securityAssertionService;
	
	private <E> void invokeWizard(String configurationItemName, Authentication user,
								  UnsafeBiConsumer<ConfigurationItemCreationWizard, CreationContext, ConfigurationItemCreationException> operation) throws OrbagSecurityException, UnmanagedObjectException,ConfigurationItemCreationException {
		ConfigurationItemDescriptor descriptor = metadataRegistry
				.getConfigurationItemDescriptorByName(configurationItemName);
		if (!descriptor.isAllowCreation()) {
			throw new RuntimeException("Configuration item type " + configurationItemName + " doesn't allows creation");
		}
		securityAssertionService.assertAuthorizationToConfigurationItemDescriptor(descriptor,user, AccessType.CREATE);
		ConfigurationItemCreationWizard configurationItemCreationWizard = visibilityManager.findFirstObject(
				configurationItemCreationWizardRegistry.getWizards(),
				FilterContext.forTargetClass(descriptor.getJavaClass()).forUser(user));
		CreationContext creationContext = new CreationContext();
		creationContext.setConfigurationItemDescriptor(descriptor);
		creationContext.setUser(user);
		operation.accept(configurationItemCreationWizard, creationContext);
	}

	public CreateRequest getCreateRequestTemplateFor(String configurationItemName, Authentication user) throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemCreationException {
		CreateRequest request = new CreateRequest();
		request.setConfigurationItemType(configurationItemName);
		SerializableFieldGroup parametersBuilder = new SerializableFieldGroup();
		try {
			invokeWizard(configurationItemName, user, (w, c) -> {
				w.buildCreateParameters(parametersBuilder, c);
			});
		}catch (ConfigurationItemCreationException e) {
			throw e;
		} catch (Throwable e) {
			throw new ConfigurationItemCreationException(e.getMessage(),e);
		}
		request.setParameters(parametersBuilder);
		return request;
	}

	public ConfigurationItemCreationFeedback<ConfigurationItemReference> create(CreateRequest request, Authentication user) throws OrbagSecurityException, UnmanagedObjectException {
		ConfigurationItemCreationFeedback<ConfigurationItemReference> feedback = new ConfigurationItemCreationFeedback<>();
		ObjectHolder<Object> holder = new ObjectHolder<>();
		try {
			invokeWizard(request.getConfigurationItemType(), user, (w, c) -> {

				w.validate(request.getParameters(),c,feedback);
				if (feedback.isOperationValid()) {
					holder.setValue(w.create(request.getParameters(), c));
				}
			});
			feedback.setResult(configurationItemReferenceService.getReference(holder.getValue()));
		}catch (ConfigurationItemCreationException e) {
			feedback.setException(e);
		}
		return feedback;
	}

}
