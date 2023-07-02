package orbag.metadata;

import java.util.List;

public class DisplayLabelUtils {
	
	public static String getDisplayLabel(Object object) {
		if (object ==null) {
			return "missing object";
		}
		if (object instanceof Displayable) {
			return ((Displayable)object).getDisplayLabel();
		}
		return getDefaultDisplayLabel(object);
	}
	
	protected static String getDefaultDisplayLabel(Object object) {
		if (object ==null) {
			return "missing object";
		}
		DisplayLabel displayLabel = object.getClass().getAnnotation(DisplayLabel.class);
		if (displayLabel!=null) {
			return displayLabel.value();
		}
		return object.toString();
	}
	
	public static String formatLabel(String displayLabel, List<?> affectedConfigurationitems) {
		if (displayLabel.indexOf("%") > -1) {
			if (affectedConfigurationitems==null || affectedConfigurationitems.isEmpty()) {
				return displayLabel.replaceAll("%", "nothing");
			} if (affectedConfigurationitems.size() == 1) {
				return displayLabel.replaceAll("%", getDisplayLabel(affectedConfigurationitems.get(0)));
			}else {
				return displayLabel.replaceAll("%",affectedConfigurationitems.size()+" cis");
			}
		}
		return displayLabel;
	}

    public static String camelToSpaced(String string) {
		if (string ==null) {
			return null;
		}
		String result="";
		for (int cnt=0; cnt < string.length(); cnt++) {
			char currentChar = string.charAt(cnt);
			if (cnt > 0 && Character.isUpperCase( currentChar)) {
				result+= " " + Character.toLowerCase(currentChar);
			} else {
				result += currentChar;
			}
		}
		return result;
    }
}
