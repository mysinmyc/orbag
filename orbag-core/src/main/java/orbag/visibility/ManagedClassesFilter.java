package orbag.visibility;

import org.springframework.stereotype.Component;

@Component
public class ManagedClassesFilter implements ClassVisibilityFilter{

	@Override
	public boolean isActiveForClass(Class<?> classToTest, FilterContext context) {
		return classToTest.getAnnotation(ManagedClasses.class) !=null;
	}

	@Override
	public boolean isClassVisibile(Class<?> classToTest, FilterContext context) {
		for ( Class<?> currentClass :  classToTest.getAnnotation(ManagedClasses.class).value()) {
			if (currentClass.isAssignableFrom(context.getTargetClass())) {
				return true;
			}
		}
		return false;
	}

}
