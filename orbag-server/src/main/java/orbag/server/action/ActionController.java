package orbag.server.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbag.action.ActionResult;
import orbag.security.OrbagSecurityException;

@RestController
@RequestMapping("/api/action")
public class ActionController {

	@Autowired
	ActionService actionService;

	@PostMapping("/getAvailable")
	public GetAvailableActionsResponse getAvailable(@RequestBody GetAvailableActionsRequest request,
			Authentication user) {
		GetAvailableActionsResponse response = new GetAvailableActionsResponse();
		response.setAvailableActions(actionService.getAvaiableActionsFor(request.getSourceCi(),request.getTargetCis(), user));
		return response;
	}

	@PostMapping("/submit")
	public SubmitActionResponse submit(@RequestBody SubmitActionRequest request, Authentication user) throws OrbagSecurityException {
		SubmitActionResponse response = new SubmitActionResponse();
		
		ActionResult result=actionService.submit(request.getSourceCi(),request.getAction(), request.getTargetCis(), user);
		
		response.setConsequences(result.getConsequences());
		response.setJobId(result.getJobId());
		response.setLink(result.getLink());
		response.setMessage(result.getMessage());
		return response;
	}
}
