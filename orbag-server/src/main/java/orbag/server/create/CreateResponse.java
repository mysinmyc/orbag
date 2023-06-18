package orbag.server.create;

import orbag.create.ConfigurationItemCreationFeedback;
import orbag.reference.ConfigurationItemReference;
import orbag.server.util.OperationResponse;
import orbag.util.OperationFeedback;
import orbag.util.OperationStatus;

public class CreateResponse extends OperationResponse {

	public CreateResponse() {
		super();
	}

	public CreateResponse(ConfigurationItemCreationFeedback<ConfigurationItemReference> feedback) {
		super(feedback);
		this.configurationItem = feedback.getResult();
	}

	ConfigurationItemReference configurationItem;

	public ConfigurationItemReference getConfigurationItem() {
		return configurationItem;
	}

	public void setConfigurationItem(ConfigurationItemReference configurationItem) {
		this.configurationItem = configurationItem;
	}
}
