package orbag.input;

public interface FieldGroupBuilder {


	default InputFieldBase<?> addFieldOfType(String name, String displayLabel, Class<?> type) throws DuplicateFieldException {
		if (type.isAssignableFrom(String.class)) {
			return addStringField(name,displayLabel);
		} else if (type.isAssignableFrom(Boolean.class)) {
			return  addBooleanField(name,displayLabel);
		} else if (type.isAssignableFrom(Integer.class)) {
			return addNumericField(name,displayLabel);
		}
		return null;
	}
	
	StringField addStringField(String name, String displayLabel) throws DuplicateFieldException;
	
	NumericField addNumericField(String name, String displayLabel) throws DuplicateFieldException;
	
	BooleanField addBooleanField(String name, String displayLabel) throws DuplicateFieldException;
}
