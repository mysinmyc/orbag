package orbag.server.create;

import java.util.function.BiConsumer;

import orbag.metadata.UnmanagedObjectException;
import orbag.reference.ConfigurationItemReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import orbag.create.ConfigurationItemWizard;
import orbag.create.ConfigurationItemWizardRegistry;
import orbag.create.CreationContext;
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
	ConfigurationItemWizardRegistry configurationItemWizardRegistry;

	@Autowired
	VisibilityManager visibilityManager;

	@Autowired
	SecurityAssertionService securityAssertionService;
	
	private <E> void invokeWizard(String configurationItemName, Authentication user,
			BiConsumer<ConfigurationItemWizard, CreationContext> operation) throws OrbagSecurityException, UnmanagedObjectException {
		ConfigurationItemDescriptor descriptor = metadataRegistry
				.getConfigurationItemDescriptorByName(configurationItemName);
		if (!descriptor.isAllowCreation()) {
			throw new RuntimeException("Configuration item type " + configurationItemName + " doesn't allows creation");
		}
		securityAssertionService.assertAuthorizationToConfigurationItemDescriptor(descriptor,user, AccessType.CREATE);
		ConfigurationItemWizard configurationItemWizard = visibilityManager.findFirstObject(
				configurationItemWizardRegistry.getWizards(),
				FilterContext.forTargetClass(descriptor.getJavaClass()).forUser(user));
		CreationContext creationContext = new CreationContext();
		creationContext.setConfigurationItemDescriptor(descriptor);
		creationContext.setUser(user);
		operation.accept(configurationItemWizard, creationContext);
	}

	public CreateRequest getCreateRequestTemplateFor(String configurationItemName, Authentication user) throws OrbagSecurityException, UnmanagedObjectException {
		CreateRequest request = new CreateRequest();
		request.setConfigurationItemType(configurationItemName);
		SerializableFieldGroup parametersBuilder = new SerializableFieldGroup();
		invokeWizard(configurationItemName, user, (w, c) -> {
			w.buildCreateParameters(parametersBuilder, c);
		});
		request.setParameters(parametersBuilder);
		return request;
	}

	public ConfigurationItemReference create(CreateRequest request, Authentication user) throws OrbagSecurityException, UnmanagedObjectException {
		ObjectHolder<Object> holder = new ObjectHolder<>();
		invokeWizard(request.getConfigurationItemType(), user, (w, c) -> {
			holder.setValue(w.create(request.getParameters(), c));
		});
		return configurationItemReferenceService.getReference(holder.getValue());
	}

}
