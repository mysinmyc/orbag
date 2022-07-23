package orbag.server.search;

import orbag.input.SerializableFieldGroup;
import orbag.search.ResultType;

public class SearchRequest {

	String configurationItemName;
	
	SerializableFieldGroup parameters;

	ResultType resultType;
	
	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	public String getConfigurationItemName() {
		return configurationItemName;
	}

	public void setConfigurationItemName(String configurationItemName) {
		this.configurationItemName = configurationItemName;
	}
	
	public SerializableFieldGroup getParameters() {
		return parameters;
	}

	public void setParameters(SerializableFieldGroup parameters) {
		this.parameters = parameters;
	}
	
	
	
}
