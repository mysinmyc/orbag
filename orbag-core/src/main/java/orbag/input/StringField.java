package orbag.input;

public class StringField extends InputFieldBase<String> {

	@Override
	public boolean isEmpty() {
		return value==null || value.trim().isEmpty();
	}
}
