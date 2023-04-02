package orbag.input;


public interface FieldValueSetter <T extends InputFieldBase<?>> {

	boolean canSetValue(Object value, InputFieldBase<?> field);
	
	void setFieldValue(Object value, T field);
}
