package orbag.input;

import java.util.List;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This interface describe methods that can be used to access to the content of a fieldgroup
 */
public interface FieldGroupConsumer {

	/**
	 * Return all the fields
	 * @return
	 */
	List<InputFieldBase<?>> getFields();

	/**
	 * return a field by name
	 * @param name
	 * @return
	 */
	InputFieldBase<?> getField(String name);

	/**
	 * return true if a field is present and is not empty
	 * @param fieldName
	 * @return
	 */
	default boolean isFilled(String fieldName) {
		InputFieldBase<?> inputField = getField(fieldName);
		if (inputField == null) {
			return false;
		}
		return !inputField.isEmpty();
	}

	/**
	 * return the value of a fields cast by user (throws an exception if the cast is invalid)
	 * @param fieldName
	 * @return
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	default <T> T getValue(String fieldName) {
		InputFieldBase<?> inputField = getField(fieldName);
		if (inputField == null) {
			return null;
		}
		return inputField.isEmpty() ? null : (T) inputField.getValue();

	}

	/**
	 * Execute an operation on a fields only if is present and is not empty
	 * @param fieldName name of field
	 * @param whenSet operation to execute on the field
	 * @param <T> cast the field to the specified type (throws an exception if the cast is invalid)
	 */
	@SuppressWarnings("unchecked")
	default <T> void whenFilled(String fieldName, BiConsumer<String, T> whenSet) {
		InputFieldBase<?> inputField = getField(fieldName);
		if (inputField != null && !inputField.isEmpty()) {
			whenSet.accept(fieldName, (T) inputField.getValue());
		}
	}

	/**
	 * return true if all the fields are empy
	 * @return
	 */
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
