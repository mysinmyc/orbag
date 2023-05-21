package orbag.samples.security;

import orbag.security.AccessPolicy;
import orbag.security.AccessRestricted;
import orbag.security.AccessType;

@AccessRestricted(
		{
			@AccessPolicy(authorities = {CustomAuthorities.IT_OPERATOR}, grants = {AccessType.READ,AccessType.USE}),
			@AccessPolicy(authorities = {CustomAuthorities.IT_USER}, grants = {AccessType.READ,AccessType.CREATE,AccessType.MODIFY,AccessType.DELETE,AccessType.USE})
		}
)
public interface InfrastructureCisPolicy{

}
