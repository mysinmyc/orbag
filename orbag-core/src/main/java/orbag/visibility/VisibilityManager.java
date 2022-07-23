package orbag.visibility;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VisibilityManager {

	@Autowired(required = false)
	List<ObjectVisibilityFilter> objectVisibilityFilters;

	@Autowired(required = false)
	List<ClassVisibilityFilter> classVisibilityFilters;

	protected <T> boolean isClassVisibile(Class<T> classToTest, FilterContext context,boolean strict) {
		boolean passedOneFilter = !strict;
		if (classVisibilityFilters != null) {
			for (ClassVisibilityFilter currentFilter : classVisibilityFilters) {
				if (currentFilter.isActiveForClass(classToTest, context) ) {
					passedOneFilter=true;
					if (!currentFilter.isClassVisibile(classToTest, context)) {
						return false;
					}
				}
			}
		}
		return passedOneFilter;
	}
	
	public <T> boolean isClassVisibile(Class<T> classToTest, FilterContext context) {
		return isClassVisibile(classToTest, context,true);
	}

	public boolean isObjectVisibile(Object objectToTest, FilterContext context) {
		boolean passedOneFilter=false;
		if (objectVisibilityFilters != null) {
			for (ObjectVisibilityFilter currentFilter : objectVisibilityFilters) {
				if (currentFilter.isActiveForObject(objectToTest, context)) {
					passedOneFilter=true;
					if (!currentFilter.isObjectVisibile(objectToTest, context)) {
						return false;
					}
				}
			}
		}
		return isClassVisibile(objectToTest.getClass(), context,!passedOneFilter);
	}

	public Collection<Class<?>> filterClasses(Collection<Class<?>> classesToFilter, FilterContext context) {
		return classesToFilter.stream().filter(i -> isClassVisibile(i, context)).toList();
	}

	public Class<?> findFirstClass(Collection<Class<?>> classesToFilter, FilterContext context) {
		return classesToFilter.stream().filter(i -> isClassVisibile(i, context)).findFirst().orElse(null);
	}

	public <T> Collection<T> filterObjects(Collection<T> objectsToFilter, FilterContext context) {
		return objectsToFilter.stream().filter(i -> isObjectVisibile(i, context)).toList();
	}

	public <T> T findFirstObject(Collection<T> objectsToFilter, FilterContext context) {
		return objectsToFilter.stream().filter(i -> isObjectVisibile(i, context)).findFirst().orElse(null);
	}
}
