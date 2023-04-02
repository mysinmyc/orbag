package orbag.input;

public @interface InputField {

	String name();
	Class<?> type();
	String displayLabel() default "";
	String defaultValue() default "";
}
