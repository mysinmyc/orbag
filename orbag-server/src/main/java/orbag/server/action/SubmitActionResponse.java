package orbag.server.action;

import orbag.action.ActionConsequences;

public class SubmitActionResponse {

	ActionConsequences consequences;
	
	String message;
	
	String link;
	
	String jobId;

	public ActionConsequences getConsequences() {
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

}
