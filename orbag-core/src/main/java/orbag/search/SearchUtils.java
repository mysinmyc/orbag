package orbag.search;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

import orbag.input.FieldGroupBuilder;
import orbag.input.FieldGroupConsumer;
import orbag.input.InputFieldBase;
import orbag.input.StringField;
import orbag.util.MyReflectionUtils;

@Component
public class SearchUtils {

	protected void buildInputFieldFromClassField(Field field, FieldGroupBuilder fieldGroupBuilder) {
		Searcheable searchableAnnotation = field.getAnnotation(Searcheable.class);
		if (searchableAnnotation == null) {
			return;
		}
		Class<?> fieldType = field.getType();
		if (fieldType.isAssignableFrom(String.class)) {
			fieldGroupBuilder.addStringField(field.getName(),
					searchableAnnotation.displayLabel().isEmpty() ? field.getName()
							: searchableAnnotation.displayLabel());
		} else if (fieldType.isAssignableFrom(Boolean.class)) {
			fieldGroupBuilder.addBooleanField(field.getName(),
					searchableAnnotation.displayLabel().isEmpty() ? field.getName()
							: searchableAnnotation.displayLabel());
		} else if (fieldType.isAssignableFrom(Integer.class)) {
			fieldGroupBuilder.addNumericField(field.getName(),
					searchableAnnotation.displayLabel().isEmpty() ? field.getName()
							: searchableAnnotation.displayLabel());
		}
	}

	public void buildSearcheableFields(Class<?> sourceClass, FieldGroupBuilder fieldGroupBuilder) {
		MyReflectionUtils.forEachDecladerField(sourceClass, f -> buildInputFieldFromClassField(f, fieldGroupBuilder));
	}

	protected void buildSearchConditionFromInputField(InputFieldBase<?> field, SearchConditions condition,
			boolean exactMatch) {

		if (field.isEmpty()) {
			return;
		}
		if (field instanceof StringField && !exactMatch) {
			condition.withCondition(field.getName(), Operator.LIKE,
					SearchCondition.WILD_CARD + field.getValue() + SearchCondition.WILD_CARD);
		} else {
			condition.withCondition(field.getName(), Operator.EQUAL, field.getValue());
		}
	}

	public SearchConditions fromFields(FieldGroupConsumer fields, boolean exactMatch) {
		SearchConditions result = SearchConditions.and();
		fields.getFields().forEach(f -> buildSearchConditionFromInputField(f, result, exactMatch));
		return result;
	}
}
