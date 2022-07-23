package orbag.data;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;

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
}
