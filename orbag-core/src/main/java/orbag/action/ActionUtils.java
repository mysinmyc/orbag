package orbag.action;

import java.util.List;

public class ActionUtils {

	public static boolean isSingleObjectOfType(List<?> objects , Class<?>targetType) {
		return areAllObjectsOfType(objects, targetType) && objects.size()==1; 
	}
	
	public static boolean areAllObjectsOfType(List<?> objects , Class<?>targetType) {
		if (objects==null || objects.isEmpty()) {
			return false;
		}
		for ( Object object : objects ) {
			if (!targetType.isInstance(object)) {
				return false;
			}
		}
		return true;
	}
}
