package orbag.server.view;

import orbag.reference.ConfigurationItemReference;

public class BindViewRequest {

	ConfigurationItemReference targetCi;
	
	SerializableView view;

	public SerializableView getView() {
		return view;
	}

	public void setView(SerializableView view) {
		this.view = view;
	}

	public ConfigurationItemReference getTargetCi() {
		return targetCi;
	}

	public void setTargetCi(ConfigurationItemReference targetCi) {
		this.targetCi = targetCi;
	}

}
