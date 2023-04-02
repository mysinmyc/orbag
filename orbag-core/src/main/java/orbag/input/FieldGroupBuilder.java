package orbag.input;

import orbag.metadata.ConfigurationItemDescriptor;

public interface FieldGroupBuilder {

	default InputFieldBase<?> addFieldOfType(String name, String displayLabel, Class<?> type) throws DuplicateFieldException {
		if (type.isEnum() ) {
			return addEnumField(name,displayLabel, type);
		}else if (type.isAssignableFrom(String.class)) {
			return addStringField(name,displayLabel);
		} else if (type.isAssignableFrom(Boolean.class)) {
			return  addBooleanField(name,displayLabel);
		} else if (type.isAssignableFrom(Integer.class)) {
			return addNumericField(name,displayLabel);
		} else {
			ConfigurationItemDescriptor descriptor = ConfigurationItemDescriptor.fromClass(type,null,false);
			if (descriptor!=null) {
				addReferenceField(name, displayLabel, descriptor.getName());
			}
		}
		return null;
	}
	
	EnumField addEnumField(String name, String displayLabel, Class<?> enumType) throws DuplicateFieldException;
	
	StringField addStringField(String name, String displayLabel) throws DuplicateFieldException;
	
	NumericField addNumericField(String name, String displayLabel) throws DuplicateFieldException;
	
	BooleanField addBooleanField(String name, String displayLabel) throws DuplicateFieldException;

	ConfigurationItemReferenceField addReferenceField(String name, String displayLabel, String configurationItemType)
			throws DuplicateFieldException;
}
