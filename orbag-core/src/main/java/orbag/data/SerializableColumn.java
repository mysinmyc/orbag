package orbag.data;

public class SerializableColumn implements PartialColumn{

	String name;

	ColumnType type;

	String displayLabel;
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(ColumnType type) {
		this.type = type;
	}

	@Override
	public ColumnType getType() {
		return type;
	}
	
	@Override
	public String getDisplayLabel() {
		return displayLabel;
	}

	@Override
	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}
	
}
