package orbag.data;

import java.util.*;
import java.util.function.Function;

import orbag.metadata.UnmanagedObjectException;
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
			try {
				return configurationItemReferenceService.getReference(value);
			} catch (UnmanagedObjectException e) {
				throw new RuntimeException(e);
			}
		}
		return value;
	}

	@Override
	public PartialRow addRow(T item) throws LimitExceededException {
		if (table.getRows().size()>=limit) {
			throw new LimitExceededException();
		}
		SerializableRow row = new SerializableRow();
		row.setFields(new HashMap<>());
		row.setTags(new HashSet<>());
		table.getRows().add(row);
		for (GeneratedColumn generatedColumn : generatedcolumns) {
			row.getFields().put(generatedColumn.column.getName(),
					castValue(generatedColumn.valueProvider.apply(item), generatedColumn.column.getType()));
		}
		try {
			setReferenceFields(item,row.getFields());
		} catch (UnmanagedObjectException e) {
		}
		return new SerializableRowBuilder(configurationItemReferenceService, row);
	}

	boolean referenceColumnsDefined=false;
	
	private void setReferenceFields(T item, Map<String, Object> row) throws UnmanagedObjectException {
		ConfigurationItemReference reference = configurationItemReferenceService.getReference(item);
		if (reference ==null) {
			return;
		}
		if (!referenceColumnsDefined) {
			addColumn(COLUMN__REFERENCE, ColumnType.Reference).setDisplayLabel("Configuration Item");
			referenceColumnsDefined=true;
		}
		row.put(COLUMN__REFERENCE, reference);
	}

	public SerializableTable build() {
		return table;
	}
}
