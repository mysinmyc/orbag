package orbag.server.action;

public class SerializableAction {


	String identifier;
	
	String displayLabel;

	boolean quick;



	public SerializableAction() {
		
	}
	
	public SerializableAction(String identifier, String displayLabel, boolean quick) {
		this.identifier = identifier;
		this.displayLabel = displayLabel;
		this.quick = quick;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public boolean isQuick() {
		return quick;
	}

	public void setQuick(boolean quick) {
		this.quick = quick;
	}
}
