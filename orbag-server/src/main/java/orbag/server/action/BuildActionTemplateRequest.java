package orbag.server.action;

public class BuildActionTemplateRequest extends ActionInputBase {

	SerializableAction action;

	public SerializableAction getAction() {
		return action;
	}

	public void setAction(SerializableAction action) {
		this.action = action;
	}
}
