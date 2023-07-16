package orbag.util;

import orbag.data.ColumnType;
import orbag.data.PartialColumn;
import orbag.data.RowBuilder;
import orbag.data.TableBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.function.Function;

public class TsvBuilder {

    Writer outputStreamWriter;

    String fieldDelimiter;

    public TsvBuilder(Writer outputStreamWriter){
        this(outputStreamWriter,"\t");
    }

    public TsvBuilder(Writer outputStreamWriter, String fieldDelimiter){
        this.outputStreamWriter = outputStreamWriter;
        this.fieldDelimiter=fieldDelimiter;
    }

    int currentRow;

    int currentColumn;

    public void addCell(String cell) throws IOException {
        if (currentColumn> 0) {
            outputStreamWriter.append(fieldDelimiter);
        }
        outputStreamWriter.append( cell == null ? "" : cell);
        currentColumn++;
    }

    public void nextRow() throws IOException {
        outputStreamWriter.append("\n");
        currentRow++;
        currentColumn=0;
    }


}
