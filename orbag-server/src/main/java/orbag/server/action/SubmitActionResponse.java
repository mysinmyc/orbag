package orbag.server.action;

import java.util.ArrayList;
import java.util.List;

import orbag.action.ActionConsequences;
import orbag.action.ActionFeedback;
import orbag.action.ActionResult;
import orbag.server.util.OperationResponse;
import orbag.util.OperationStatus;
import orbag.util.ValidationError;

public class SubmitActionResponse extends OperationResponse {

	public SubmitActionResponse() {
		super();
	}

	public SubmitActionResponse(ActionFeedback feedback) {
		super(feedback);
		ActionResult result = feedback.getResult();
		if (result!=null) {
			setConsequences(result.getConsequences());
			setJobId(result.getJobId());
			setLink(result.getLink());
			setMessage(result.getMessage());
		}
	}

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


