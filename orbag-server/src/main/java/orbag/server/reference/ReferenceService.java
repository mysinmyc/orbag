package orbag.server.reference;

import orbag.dao.ConfigurationItemNotFoundException;
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataRegistry;
import orbag.metadata.UnmanagedObjectException;
import orbag.security.AccessType;
import orbag.security.OrbagSecurityException;
import orbag.security.SecurityAssertionService;
import orbag.server.OrbagServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;

@Component
public class ReferenceService {

	@Autowired
	ConfigurationItemReferenceService configurationItemReferenceService;

	@Autowired
	MetadataRegistry metadataRegistry;

	@Autowired
	SecurityAssertionService securityAssertionService;

	@Autowired
	ConfigurationItemDao dao;

	public ConfigurationItemReference loadReference(String configurationItemId, String configurationItemType, Authentication user) throws OrbagSecurityException, UnmanagedObjectException, ConfigurationItemNotFoundException {

		Object ci = dao.getExistingCiOrThrow(new ConfigurationItemReference(configurationItemId, configurationItemType));

		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry
				.getConfigurationItemDescriptorByClass(ci.getClass());

		securityAssertionService.assertAuthorizationToConfigurationItemDescriptor(configurationItemDescriptor,user, AccessType.USE,AccessType.MODIFY,AccessType.READ);

		return (ConfigurationItemReference) configurationItemReferenceService
				.getReference(ci);
	}
}
