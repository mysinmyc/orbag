package orbag.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SerializableTable {


	List<SerializableColumn> columns = new ArrayList<SerializableColumn>();

	
	List<SerializableRow> rows = new ArrayList<SerializableRow>();
	
	public List<SerializableColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<SerializableColumn> columns) {
		this.columns = columns;
	}

	public List<SerializableRow> getRows() {
		return rows;
	}

	public void setRows(List<SerializableRow> rows) {
		this.rows = rows;
	}


}
