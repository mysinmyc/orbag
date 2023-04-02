package orbag.input;

import org.springframework.stereotype.Component;

@Component
public class EnumFieldConverter implements FieldValueGetter<EnumField>, FieldValueSetter<EnumField> {

	@Override
	public boolean canSetValue(Object value, InputFieldBase<?> field) {
		return field instanceof EnumField;
	}

	@Override
	public void setFieldValue(Object value, EnumField field) {
		field.setValue(value==null?null:value.toString());
	}

	@Override
	public boolean canGetFieldValue(InputFieldBase<?> field, Class<?> modelType) {
		return field instanceof EnumField;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object fieldToValue(EnumField field, Class<?> modelType) {
		return Enum.valueOf((Class) modelType, field.getValue());
	}

}
