package orbag.visibility;

import org.springframework.security.core.Authentication;

public class FilterContext {

	Object targetObject;
	
	Class<?> targetClass;
	
	Authentication user;
		
	private FilterContext() {
	}

	public Object getTargetObject() {
		return targetObject;
	}
	
	public Class<?> getTargetClass() {
		return targetClass;
	}
	
	public static FilterContext forTargetObject(Object targetObject) {
		FilterContext filterContext = new FilterContext();
		filterContext.targetObject = targetObject;
		filterContext.targetClass = targetObject.getClass();
		return filterContext;
	}
	
	public static FilterContext forTargetClass(Class<?> targetClass) {
		FilterContext filterContext = new FilterContext();
		filterContext.targetClass = targetClass;
		return filterContext;
	}
	
	public static FilterContext withoutTarget() {
		return new FilterContext();
	}
	
	public FilterContext forUser(Authentication user) {
		this.user = user;
		return this;
	}
	
	public Authentication getUser() {
		return user;
	}
}
