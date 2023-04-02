package orbag.server.view;

import orbag.data.SerializableTable;

public class BindViewResponse {

	SerializableTable resultTable;

	public SerializableTable getResultTable() {
		return resultTable;
	}

	public void setResultTable(SerializableTable resultTable) {
		this.resultTable = resultTable;
	}
}
