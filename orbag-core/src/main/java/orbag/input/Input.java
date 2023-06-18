package orbag.input;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Consumer;

/**
 * This annotation describe inputs.
 *
 * Use {@link InputUtils#addFieldsFromAnnotatedClass(Class, FieldGroupBuilder)} to add fields to fieldgroup from an annotated class
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Input {

	/**
	 * Array of input fields
	 * @return
	 */
	InputField[] value();
}
