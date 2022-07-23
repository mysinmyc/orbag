package orbag.input;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SerializableFieldGroup implements FieldGroupBuilder, FieldGroupConsumer {

	@Override
	public List<InputFieldBase<?>> getFields() {
		return fields;
	}

	public void setFields(List<InputFieldBase<?>> fields) {
		this.fields = fields;
	}

	List<InputFieldBase<?>> fields = new ArrayList<>();

	public InputFieldBase<?> getField(String name) {
		for (InputFieldBase<?> current : fields) {
			if (current.getName().equals(name)) {
				return current;
			}
		}
		return null;
	}

	private <T extends InputFieldBase<?>> T buildInternal(String name, String displayLabel, Supplier<T> supplier)
			throws DuplicateFieldException {
		if (getField(name) != null) {
			throw new DuplicateFieldException("duplicate field " + name);
		}
		T newField = supplier.get();
		newField.setName(name);
		newField.setDisplayLabel(displayLabel);
		fields.add(newField);
		return newField;
	}

	@Override
	public StringField addStringField(String name, String displayLabel) throws DuplicateFieldException {
		return buildInternal(name, displayLabel, StringField::new);
	}

	@Override
	public NumericField addNumericField(String name, String displayLabel) throws DuplicateFieldException {
		return buildInternal(name, displayLabel, NumericField::new);
	}

	@Override
	public BooleanField addBooleanField(String name, String displayLabel) throws DuplicateFieldException {
		return buildInternal(name, displayLabel, BooleanField::new);
	}



}
