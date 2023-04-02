package orbag.action;

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
	
	
}
