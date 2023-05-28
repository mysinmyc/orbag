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
		return classToTest.getAnnotation(AccessRestricted.class) != null;
	}

	public boolean isAccessToMethodRestricted(Method method) {
		return method.getAnnotation(AccessRestricted.class) != null;
	}

	public Grants getAccessRightsToObjectFor(Object object, Authentication user) {
		return getAccessRightsToClassFor(user, object.getClass());
	}

	public Grants getAccessRightsToClassFor(Authentication user, Class<?> classToTest) {
		return PermissionsUtils.getAccess(user, classToTest);
	}

	public Grants getAccessRightsToMethodsFor(Authentication user, Method... methodToTest) {
		return PermissionsUtils.getAccessToMethods(user, methodToTest);
	}

	public Grants getAccessRightsToConfigurationItemDescriptor(ConfigurationItemDescriptor configurationItemDescriptor,
										 Authentication user) {
		return getAccessRightsToClassFor(user,configurationItemDescriptor.getJavaClass());
	}

	public boolean hasAuthorizationToConfigurationItemDescriptor(ConfigurationItemDescriptor configurationItemDescriptor,
			Authentication user, AccessType...accessTypes) {
		return getAccessRightsToConfigurationItemDescriptor(configurationItemDescriptor,user).hasAnyAccess(accessTypes);
	}

	public void assertAuthorizationToConfigurationItemDescriptor(ConfigurationItemDescriptor configurationItemDescriptor,
			Authentication user, AccessType... accessTypes) throws OrbagSecurityException {
		if (!hasAuthorizationToConfigurationItemDescriptor(configurationItemDescriptor,user, accessTypes)) {
			throw new OrbagSecurityException(user, configurationItemDescriptor, accessTypes);
		}
	}

	public Grants getAccessRightsToConfigurationItemPropertyDescriptor(ConfigurationItemPropertyDescriptor configurationItemPropertyDescriptor,
																	   Authentication user) {
		Grants result = getAccessRightsToMethodsFor(user, configurationItemPropertyDescriptor.getGetterMethod(), configurationItemPropertyDescriptor.getSetterMethod());
		if (result.isExplicit()) {
			return result;
		} else {
			return getAccessRightsToConfigurationItemDescriptor(configurationItemPropertyDescriptor.getConfigurationItem(),user);
		}
	}

	public boolean hasAuthorizationToConfigurationItemPropertyDescriptor(ConfigurationItemPropertyDescriptor configurationItemPropertyDescriptor,
			Authentication user, AccessType... accessTypes) {
		return getAccessRightsToConfigurationItemPropertyDescriptor(configurationItemPropertyDescriptor,user).hasAnyAccess(accessTypes);
	}

	public void assertAuthorizationToConfigurationItemPropertyDescriptor(ConfigurationItemPropertyDescriptor configurationItemPropertyDescriptor,
			Authentication user, AccessType... accessTypes) throws OrbagSecurityException {
		if (!hasAuthorizationToConfigurationItemPropertyDescriptor(configurationItemPropertyDescriptor, user, accessTypes)) {
			throw new OrbagSecurityException(user, configurationItemPropertyDescriptor, accessTypes);
		}
	}

}
