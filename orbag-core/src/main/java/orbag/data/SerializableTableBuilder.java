package orbag.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.util.LimitExceededException;

public class SerializableTableBuilder<T> implements TableBuilder<T>, RowBuilder<T>,ColumnBuilder<T> {

	public static  String COLUMN__REFERENCE="__reference";
	
	ConfigurationItemReferenceService configurationItemReferenceService;

	SerializableTable table = new SerializableTable();

	public SerializableTableBuilder(ConfigurationItemReferenceService configurationItemReferenceService) {
		this.configurationItemReferenceService = configurationItemReferenceService;
	}

	int limit=Integer.MAX_VALUE;
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public PartialColumn addColumn(String name, ColumnType columnType) {
		SerializableColumn column = new SerializableColumn();
		column.setName(name);
		column.setType(columnType);
		table.columns.add(column);
		return column;
	}

	class GeneratedColumn {
		SerializableColumn column;
		Function<T, Object> valueProvider;
	}

	List<GeneratedColumn> generatedcolumns = new ArrayList<>();

	@Override
	public PartialColumn addGeneratedColumn(String name, ColumnType columnType,
			Function<T, Object> valueProvider) {
		SerializableColumn column = (SerializableColumn) addColumn(name, columnType);
		GeneratedColumn generatedColumn = new GeneratedColumn();
		generatedColumn.column = column;
		generatedColumn.valueProvider = valueProvider;
		generatedcolumns.add(generatedColumn);
		return column;
	}

	@Override
	public RowBuilder<T> rows() {
		return this;
	}

	private Object castValue(Object value, ColumnType columnType) {
		if (value == null) {
			return null;
		}
		if (columnType.equals(ColumnType.Reference)) {
			return configurationItemReferenceService.getReference(value);
		}
		return value;
	}

	@Override
	public PartialRow addRow(T item) throws LimitExceededException {
		if (table.getRows().size()>=limit) {
			throw new LimitExceededException();
		}
		Map<String, Object> row = new HashMap<String, Object>();
		table.getRows().add(row);
		for (GeneratedColumn generatedColumn : generatedcolumns) {
			row.put(generatedColumn.column.getName(),
					castValue(generatedColumn.valueProvider.apply(item), generatedColumn.column.getType()));
		}
		setReferencesFields(item,row);
		return new SerializableRowBuilder(configurationItemReferenceService, row);
	}

	boolean referenceColumnsDefined=false;
	
	private void setReferencesFields(T item, Map<String, Object> row) {
		ConfigurationItemReference reference = configurationItemReferenceService.getReference(item);
		if (reference ==null) {
			return;
		}
		if (!referenceColumnsDefined) {
			addColumn(COLUMN__REFERENCE, ColumnType.Reference);
		}
		row.put(COLUMN__REFERENCE, reference);
	}

	public SerializableTable build() {
		return table;
	}
}
