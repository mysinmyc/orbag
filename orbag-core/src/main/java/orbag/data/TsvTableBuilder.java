package orbag.data;

import orbag.metadata.DisplayLabelUtils;
import orbag.metadata.UnmanagedObjectException;
import orbag.util.LimitExceededException;
import orbag.util.TsvBuilder;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TsvTableBuilder<T> implements TableBuilder<T>, RowBuilder<T>, PartialRow, AutoCloseable{

    TsvBuilder tsvBuilder;

    public TsvTableBuilder(OutputStreamWriter outputStreamWriter) {
        this.tsvBuilder = new TsvBuilder(outputStreamWriter);
    }



    static class TsvInternalColumn<T> extends  SerializableColumn {
        Function<T, Object> valueProvider;
    }

    List<TsvInternalColumn> columns = new ArrayList<>();

    boolean headerWritten=false;

    @Override
    public PartialColumn addColumn(String name, ColumnType columnType) {
        if (headerWritten) {
            throw new RuntimeException("Cannot add column after, header has been already written");
        }
        TsvInternalColumn column = new TsvInternalColumn();
        column.setName(name);
        column.setType(columnType);
        columns.add(column);
        return column;
    }

    @Override
    public PartialColumn addGeneratedColumn(String name, ColumnType columnType, Function<T, Object> valueProvider) {
        PartialColumn column =  addColumn(name,columnType);
        ((TsvInternalColumn)column).valueProvider =valueProvider;
        return column;
    }

    @Override
    public RowBuilder<T> rows() {
        return this;
    }


    T currentRowItem;

    Map<String,Object> currentRow;

    void writeHeader() throws IOException {
        if (headerWritten) {
            return;
        }
        for (TsvInternalColumn currentColumn : columns) {
            tsvBuilder.addCell(currentColumn.getDisplayLabel() == null || currentColumn.getDisplayLabel().isEmpty() ? currentColumn.getName() : currentColumn.getDisplayLabel());
        }
        tsvBuilder.nextRow();
        headerWritten=true;
    }
    void writeCurrentRow() throws IOException {
        writeHeader();
        if (currentRow!=null && !currentRow.isEmpty()) {
            for (TsvInternalColumn currentColumn : columns) {
                    tsvBuilder.addCell(""+currentRow.get(currentColumn.getName()));
            }
            tsvBuilder.nextRow();
        }
    }

    void resolveGeneratedValues() {
        for (TsvInternalColumn currentColumn : columns) {
            if (currentColumn.valueProvider != null) {
                currentRow.put(currentColumn.getName(), currentColumn.valueProvider.apply(currentRowItem));
            }
        }
    }

    @Override
    public PartialRow addRow(T item) throws LimitExceededException {
        try {
            writeCurrentRow();
            currentRow=new HashMap<>();
            currentRowItem=item;
            resolveGeneratedValues();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public void close() throws Exception {
        writeCurrentRow();
    }

    @Override
    public PartialRow withValue(String columnName, Object value) {
        currentRow.put(columnName,value);
        return this;
    }

    @Override
    public PartialRow withReference(String columnName, Object configurationItem) throws UnmanagedObjectException {
        currentRow.put(columnName, DisplayLabelUtils.getDisplayLabel(configurationItem));
        return this;
    }

    @Override
    public PartialRow withTag(String tag) {
        return this;
    }
}
