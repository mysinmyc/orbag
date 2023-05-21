package orbag.samples.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import orbag.metadata.ConfigurationItem;
import orbag.security.AccessRestricted;

@Entity
@ConfigurationItem
@AccessRestricted()
public class InternalUser {

	@Id
	String id;
}
