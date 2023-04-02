package orbag.input;

public class NumericField extends InputFieldBase<Integer> {

	@Override
	public void parseValue(String value) {
		setValue(Integer.parseInt(value));
	}

}
