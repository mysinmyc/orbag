package orbag.server.action;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/action")
public class ActionController {

	@Autowired
	ActionService actionService;
	
	@PostMapping("/getAvailable")
	public GetAvailableActionsResponse getAvailable(@RequestBody GetAvailableActionsRequest request) {
		GetAvailableActionsResponse response = new GetAvailableActionsResponse();
		response.setAvailableActions(actionService.getAvaiableActionsFor(request.getTargetCis()));
		return response;
	}
	
	@PostMapping("/submit")
	public SubmitActionResponse submit(@RequestBody SubmitActionRequest request) {
		SubmitActionResponse response = new SubmitActionResponse();
		response.setJobId(UUID.randomUUID().toString());
		response.setMessage( actionService.submit(request.getAction(), request.getTargetCis()));
		return response;
	}
}
