package orbag.input;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldManagementUtils {

	@Autowired
	List<FieldValueSetter<?>> fieldValueSetters;
	
	@Autowired
	List<FieldValueGetter<?>> fieldValueGetters;
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object fieldToValue(InputFieldBase<?> field, Class<?> modelType) {
		
		for (FieldValueGetter current: fieldValueGetters) {
			if (current.canGetFieldValue(field, modelType)) {
				return current.fieldToValue(field, modelType);
			}
		}
		
		return field.getValue();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setFieldValue( Object value, InputFieldBase<?> field) {
		for (FieldValueSetter current : fieldValueSetters) {
			if (current.canSetValue(value, field)) {
				current.setFieldValue(value, field);
				return;
			}
		}
		((InputFieldBase)field).setValue(value);
	}
	
	
}
