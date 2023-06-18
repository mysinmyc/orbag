package orbag.input;

import java.util.function.Consumer;

public class InputUtils {

	public static boolean forEachAnnotatedField(Class<?> annotatedClass, Consumer<InputField> consumer) {
		Input inputAnnotation = annotatedClass.getAnnotation(Input.class);
		if (inputAnnotation == null) {
			return false;
		}
		for (InputField fieldAnnotation : inputAnnotation.value()) {
			consumer.accept(fieldAnnotation);
		}
		return true;
	}

	public static boolean addFieldsFromAnnotatedClass(Class<?> annotatedClass, FieldGroupBuilder builder) {
		return forEachAnnotatedField(annotatedClass, (InputField fieldAnnotation)->{;
			InputFieldBase<?> field = builder.addFieldOfType(fieldAnnotation.name(), fieldAnnotation.displayLabel(),
					fieldAnnotation.type());
			if (!fieldAnnotation.defaultValue().isEmpty()) {
				field.parseValue(fieldAnnotation.defaultValue());
			}
		});

	}

}
