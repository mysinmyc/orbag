package orbag.input;


public interface FieldValueGetter<T extends InputFieldBase<?>> {

	boolean canGetFieldValue(InputFieldBase<?> field, Class<?> modelType);
	
	Object fieldToValue(T field, Class<?> modelType);
}
