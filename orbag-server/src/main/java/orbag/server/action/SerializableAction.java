package orbag.server.action;

public class SerializableAction {

	String identifier;
	
	String displayLabel;

	public SerializableAction() {
		
	}
	
	public SerializableAction(String identifier, String displayLabel) {
		this.identifier = identifier;
		this.displayLabel = displayLabel;
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
}
