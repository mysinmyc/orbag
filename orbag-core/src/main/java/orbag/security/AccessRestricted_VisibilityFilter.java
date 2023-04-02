package orbag.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.visibility.ClassVisibilityFilter;
import orbag.visibility.FilterContext;

@Component
public class AccessRestricted_VisibilityFilter implements ClassVisibilityFilter {

	@Autowired
	SecurityAssertionService securityAssertionService;

	@Override
	public boolean isActiveForClass(Class<?> classToTest, FilterContext context) {
		return securityAssertionService.isAccessToClassRestricted(classToTest);
	}

	@Override
	public boolean isClassVisibile(Class<?> classToTest, FilterContext context) {
		return securityAssertionService.getAccessRightsToClassFor(context.getUser(),classToTest).hasAccess(AccessType.USE);
	}

}
