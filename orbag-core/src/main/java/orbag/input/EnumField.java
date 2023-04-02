package orbag.input;

import java.util.List;

public class EnumField extends InputFieldBase<String> {

	
	List<String> allowedValues;

	public List<String> getAllowedValues() {
		return allowedValues;
	}

	public void setAllowedValues(List<String> allowedValues) {
		this.allowedValues = allowedValues;
	}
	
	
	@Override
	public void parseValue(String value) {
		setValue(value);
	}


}
