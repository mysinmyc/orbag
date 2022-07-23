package orbag.data;

public interface PartialColumn {

	String getName();

	ColumnType getType();
	
	String getDisplayLabel();
	
	void setDisplayLabel(String displayLabel);

}
