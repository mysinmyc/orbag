package orbag.security;

import java.lang.reflect.Method;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class PermissionsUtils {

	public static boolean hasAuthority(Authentication user, String authority) {
		if (EmbeddedAuthorities.ANY.equalsIgnoreCase(authority)) {
			return true;
		}
		if (user==null) {
			return false;
		}
		if (EmbeddedAuthorities.AUTHENTICATED.equalsIgnoreCase(authority)) {
			return user.isAuthenticated();
		}
		for (GrantedAuthority curAuthority: user.getAuthorities()) {
               if (curAuthority.getAuthority().equalsIgnoreCase(authority)) {
                       return true;
               }
		}
		return false;
	}
	
	public static Grants getAccess(Authentication user, Class<?> classToTest) {
		Grants result = new Grants();
		if ( ! mergeClassGrantsInto(result, user, classToTest) ) {
			result.setExplicit(false);
			result.addAll();			
		} else {
			result.setExplicit(true);
		}
		return result;
	}
	
	public static Grants getAccessToMethods(Authentication user, Method... methodsToTest) {
		Grants result = new Grants();
		boolean foundRestrictions=false;
		for (Method methodToTest: methodsToTest) {
			if (methodToTest!=null) {
				if (  mergeMethodGrantsInto(result, user, methodToTest) ) {
					foundRestrictions=true;
				}
			}
		}
		result.setExplicit(foundRestrictions);
		if (!foundRestrictions) {
			result.addAll();
		}
		return result;
	}
	
	protected static boolean mergeClassGrantsInto(Grants grants,Authentication user, Class<?> classToTest) {
		AccessRestricted annotation = classToTest.getAnnotation(AccessRestricted.class);
		if (annotation==null) {
			return false;
		}
		mergeAccessPoliciesGrantedInto(grants, user, annotation.value());
		for (Class<?> currentClass : annotation.inherts()) {
			if (grants.addSource(currentClass.getName())) {
				mergeClassGrantsInto(grants, user, currentClass);
			}
		}
		return true;
	}
	
	protected static boolean mergeMethodGrantsInto(Grants grants,Authentication user, Method methodToTest) {
		AccessRestricted annotation = methodToTest.getAnnotation(AccessRestricted.class);
		if (annotation==null) {
			return false;
		}
		mergeAccessPoliciesGrantedInto(grants, user, annotation.value());
		for (Class<?> currentClass : annotation.inherts()) {
			if (grants.addSource(currentClass.getName())) {
				mergeClassGrantsInto(grants, user, currentClass);
			}
		}
		return true;
	}
	
	protected static void mergeAccessPoliciesGrantedInto(Grants grants, Authentication user, AccessPolicy... policies) {
		for (AccessPolicy policy : policies) {
			boolean policyGranted = policy.allRequired();
			for (String authority :policy.authorities()) {
				boolean hasPermission = hasAuthority(user, authority);
				if (policy.allRequired()) {
					if (!hasPermission) {
						policyGranted = false;
						break;
					}
				} else {
					if (hasPermission) {
						policyGranted = true;
						break;
					}
				}
			}
			if (policyGranted) {
				grants.add(policy.grants());
			}
		}
	}


}
