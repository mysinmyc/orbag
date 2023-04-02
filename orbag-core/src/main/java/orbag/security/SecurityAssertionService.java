package orbag.security;

import java.lang.reflect.Method;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.ConfigurationItemPropertyDescriptor;

@Component
public class SecurityAssertionService {

	public boolean isAccessToObjectRestricted(Object object) {
		return isAccessToClassRestricted(object.getClass());
	}
	
	public boolean isAccessToClassRestricted(Class<?> classToTest) {
		return classToTest.getAnnotation(AccessRestricted.class)!=null;
	}
	
	public boolean isAccessToMethodRestricted(Method method) {
		return method.getAnnotation(AccessRestricted.class)!=null;
	}
	
	public Grants getAccessRightsToObjectFor(Object object, Authentication user) {
		return getAccessRightsToClassFor(user,object.getClass());
	}
	
	public Grants getAccessRightsToClassFor(Authentication user,Class<?> classToTest) {
		return PermissionsUtils.getAccess(user,classToTest);	
	}

	public Grants getAccessRightsToMethodsFor(Authentication user,Method... methodToTest) {
		return PermissionsUtils.getAccessToMethods(user, methodToTest);	
	}
	
	public void assertAuthorizationToConfigurationItemDescriptor(ConfigurationItemDescriptor descriptor,
			Authentication user, AccessType...accessTypes) throws OrbagSecurityException {
		if (!getAccessRightsToClassFor(user, descriptor.getJavaClass()).hasAnyAccess(accessTypes)) {
			throw new OrbagSecurityException(user, descriptor, accessTypes);
		}
	}

	public boolean hasAuthorizationToConfigurationItemPropertyDescriptor(ConfigurationItemPropertyDescriptor descriptor,
			Authentication user, AccessType...accessTypes) {
		return getAccessRightsToMethodsFor(user, descriptor.getGetterMethod(), descriptor.getSetterMethod())
				.hasAnyAccess(accessTypes);
	}
	
	public void assertAuthorizationToConfigurationItemPropertyDescriptor(ConfigurationItemPropertyDescriptor descriptor,
			Authentication user, AccessType...accessTypes) throws OrbagSecurityException {
		if (!hasAuthorizationToConfigurationItemPropertyDescriptor(descriptor, user, accessTypes)) {
			throw new OrbagSecurityException(user, descriptor, accessTypes);
		}
	}
	
}
