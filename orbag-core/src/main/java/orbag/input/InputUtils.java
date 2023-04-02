package orbag.input;

public class InputUtils {

	public static void addFieldsFromAnnotatedClass(Class<?> annotatedClass, FieldGroupBuilder builder) {
		Input inputAnnotation = annotatedClass.getAnnotation(Input.class);
		if (inputAnnotation == null) {
			return;
		}
		for (InputField fieldAnnotation : inputAnnotation.value()) {
			InputFieldBase<?> field = builder.addFieldOfType(fieldAnnotation.name(), fieldAnnotation.displayLabel(),
					fieldAnnotation.type());
			if (!fieldAnnotation.defaultValue().isEmpty()) {
				field.parseValue(fieldAnnotation.defaultValue());
			}
		}
	}
}
