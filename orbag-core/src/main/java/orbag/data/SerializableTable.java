package orbag.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SerializableTable {


	List<SerializableColumn> columns = new ArrayList<SerializableColumn>();

	
	List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
	
	public List<SerializableColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<SerializableColumn> columns) {
		this.columns = columns;
	}

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}


}
