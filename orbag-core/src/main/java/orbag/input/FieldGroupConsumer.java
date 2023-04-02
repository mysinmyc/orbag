package orbag.input;

import java.util.List;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface FieldGroupConsumer {

	List<InputFieldBase<?>> getFields();

	InputFieldBase<?> getField(String name);

	default boolean isFilled(String fieldName) {
		InputFieldBase<?> inputField = getField(fieldName);
		if (inputField == null) {
			return false;
		}
		return !inputField.isEmpty();
	}

	@SuppressWarnings("unchecked")
	default <T> T getValue(String fieldName) {
		InputFieldBase<?> inputField = getField(fieldName);
		if (inputField == null) {
			return null;
		}
		return inputField.isEmpty() ? null : (T) inputField.getValue();

	}

	@SuppressWarnings("unchecked")
	default <T> void whenFilled(String fieldName, BiConsumer<String, T> whenSet) {
		InputFieldBase<?> inputField = getField(fieldName);
		if (inputField != null && !inputField.isEmpty()) {
			whenSet.accept(fieldName, (T) inputField.getValue());
			;
		}
	}

	@JsonIgnore
	default boolean isEmpty() {
		for  (InputFieldBase<?> field : getFields()) {
			if (!field.isEmpty()) {
				return false;
			}
		}
		return true;
	}

}
