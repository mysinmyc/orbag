package orbag.data;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;

import orbag.input.FieldGroupBuilder;
import orbag.input.InputFieldBase;
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.ConfigurationItemPropertyDescriptor;

public class DataUtils {

	protected static Object getValue(ConfigurationItemPropertyDescriptor property, Object object) {
		try {
			return property.getGetterMethod().invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return null;
		}	
	}
	
	public static <T> void buildColumnsFromConfigurationItemDescriptor( ConfigurationItemDescriptor configurationItemDescriptor, ColumnBuilder<T> columnBuilder) {
		buildColumnsFromConfigurationItemDescriptor(configurationItemDescriptor, columnBuilder,null);
	}
	
	public static <T> void buildColumnsFromConfigurationItemDescriptor( ConfigurationItemDescriptor configurationItemDescriptor, ColumnBuilder<T> columnBuilder, Predicate<ConfigurationItemPropertyDescriptor> propertiesFilter) {
		for (ConfigurationItemPropertyDescriptor property : configurationItemDescriptor.getProperties()) {
			if (propertiesFilter ==null || propertiesFilter.test(property)) {
				PartialColumn column= columnBuilder.addGeneratedColumn(property.getName(), property.isConfigurationItemReference() ? ColumnType.Reference : ColumnType.Primitive, (i) -> getValue(property, i));
				column.setDisplayLabel(property.getDisplayLabel());
			}
		}
	}
	
	public static InputFieldBase<?> buildInputFieldFromConfigurationItemProperty(ConfigurationItemPropertyDescriptor property, FieldGroupBuilder fieldGroupBuilder) {


		InputFieldBase<?> field = null;

		if ( property.isConfigurationItemReference() ) {
			field = property.isCollection() ?
					fieldGroupBuilder.addReferenceListField(property.getName(), property.getDisplayLabel(), property.getReferencedConfigurationItemType().getName())
					: fieldGroupBuilder.addReferenceField(property.getName(), property.getDisplayLabel(), property.getReferencedConfigurationItemType().getName());
		} else {
			field = fieldGroupBuilder.addFieldOfType(property.getName(), property.getDisplayLabel(), property.getValueType());
		}

		if (field!=null) {
			field.setCategory(property.getCategory());
		}
		return field;
	}
}
