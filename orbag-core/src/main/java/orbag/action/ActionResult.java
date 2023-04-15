package orbag.action;

import java.util.ArrayList;
import java.util.List;

public class ActionResult {

	public ActionConsequences getConsequences() {
		if (consequences==null) {
			return  ActionConsequences.UNDEFINED;
		}
		return consequences;
	}

	public void setConsequences(ActionConsequences consequences) {
		this.consequences = consequences;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	ActionConsequences consequences;
	
	String message;
	
	String link;
	
	String jobId;
	
	List<ValidationError> validationErrors = new ArrayList<ValidationError>();

	public List<ValidationError> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<ValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}

	public void addValidationError(String error) {
		addValidationError(error,null);
	}
	
	public void addValidationError(String error, String fieldName) {
		ValidationError validationError = new ValidationError();
		validationError.setError(error);
		validationError.setField(fieldName);
		validationErrors.add(validationError);
	}
	
	public boolean isRequestValid() {
		return validationErrors ==null || validationErrors.isEmpty();
	}
	
}
