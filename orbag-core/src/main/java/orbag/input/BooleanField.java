package orbag.input;

public class BooleanField extends InputFieldBase<Boolean> {

	@Override
	public void parseValue(String value) {
		setValue(Boolean.parseBoolean(value));
	}

}
