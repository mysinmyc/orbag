package orbag.server.action;

import orbag.input.SerializableFieldGroup;

public class SubmitActionRequest extends ActionInputBase{
	
	SerializableAction action;
	
	public SerializableAction getAction() {
		return action;
	}

	public void setAction(SerializableAction action) {
		this.action = action;
	}
	
	SerializableFieldGroup parameters;

	public SerializableFieldGroup getParameters() {
		return parameters;
	}

	public void setParameters(SerializableFieldGroup parameters) {
		this.parameters = parameters;
	}
}
