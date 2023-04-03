package orbag.action;

import java.util.List;

import org.springframework.security.core.Authentication;

import orbag.input.FieldGroupConsumer;

public class ActionRequest {

	Authentication submitter;
	
	public Authentication getSubmitter() {
		return submitter;
	}

	public void setSubmitter(Authentication submitter) {
		this.submitter = submitter;
	}
	
	Object sourceCi;
	
	public boolean hasSourceCi() {
		return sourceCi !=null;
	}
	
	public Object getSourceCi() {
		return sourceCi;
	}

	public void setSourceCi(Object sourceCi) {
		this.sourceCi = sourceCi;
	}

	List<?> targetCis;

	public List<?> getTargetCis() {
		return targetCis;
	}

	public void setTargetCis(List<?> targetCis) {
		this.targetCis = targetCis;
	}
	
	FieldGroupConsumer parameters;

	public FieldGroupConsumer getParameters() {
		return parameters;
	}

	public void setParameters(FieldGroupConsumer parameters) {
		this.parameters = parameters;
	}

	
}
