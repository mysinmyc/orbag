package orbag.input;

public interface FieldGroupBuilder {

	StringField addStringField(String name, String displayLabel) throws DuplicateFieldException;
	
	NumericField addNumericField(String name, String displayLabel) throws DuplicateFieldException;
	
	BooleanField addBooleanField(String name, String displayLabel) throws DuplicateFieldException;
}
