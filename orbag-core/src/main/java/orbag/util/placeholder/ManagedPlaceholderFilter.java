package orbag.util.placeholder;

import orbag.visibility.ClassVisibilityFilter;
import orbag.visibility.FilterContext;
import orbag.visibility.ManagedClasses;
import org.springframework.stereotype.Component;

@Component
public class ManagedPlaceholderFilter implements ClassVisibilityFilter {

	@Override
	public boolean isActiveForClass(Class<?> classToTest, FilterContext context) {
		return classToTest.getAnnotation(ManagedPlaceholder.class) !=null;
	}

	@Override
	public boolean isClassVisibile(Class<?> classToTest, FilterContext context) {
		String  idPrefix= classToTest.getAnnotation(ManagedPlaceholder.class).id_prefix();
		if (context.getTargetObject() == null) {
			return false;
		}
		if (! (context.getTargetObject() instanceof  PlaceholderConfigurationItem)) {
			return false;
		}
		PlaceholderConfigurationItem placeholderConfigurationItem = (PlaceholderConfigurationItem) context.getTargetObject();
		if (placeholderConfigurationItem.getIdentifier()==null) {
			return false;
		}
		return placeholderConfigurationItem.getIdentifier().startsWith(idPrefix);
	}

}
