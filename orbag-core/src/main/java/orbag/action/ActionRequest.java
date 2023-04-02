package orbag.action;

import java.util.List;

import org.springframework.security.core.Authentication;

public class ActionRequest {

	Authentication submitter;
	
	public Authentication getSubmitter() {
		return submitter;
	}

	public void setSubmitter(Authentication submitter) {
		this.submitter = submitter;
	}

	List<?> targetCis;

	public List<?> getTargetCis() {
		return targetCis;
	}

	public void setTargetCis(List<?> targetCis) {
		this.targetCis = targetCis;
	}
}
