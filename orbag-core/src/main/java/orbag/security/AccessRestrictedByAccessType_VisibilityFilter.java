package orbag.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.visibility.ClassVisibilityFilter;
import orbag.visibility.FilterContext;

@Component
public class AccessRestrictedByAccessType_VisibilityFilter implements ClassVisibilityFilter {

	@Autowired
	SecurityAssertionService securityAssertionService;
	
	@Override
	public boolean isActiveForClass(Class<?> classToTest, FilterContext context) {
		return classToTest.getAnnotation(AccessRestrictedByAccessType.class) !=null;
	}

	@Override
	public boolean isClassVisibile(Class<?> classToTest, FilterContext context) {
		AccessType requiredAccessType= classToTest.getAnnotation(AccessRestrictedByAccessType.class).value();
		return securityAssertionService.getAccessRightsToObjectFor(context.getTargetObject(), context.getUser()).hasAccess(requiredAccessType);
	}
	
}
