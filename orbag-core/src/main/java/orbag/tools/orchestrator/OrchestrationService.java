package orbag.tools.orchestrator;

import org.springframework.stereotype.Component;

import orbag.orchestrator.api.InvokeJobRequest;
import orbag.orchestrator.api.InvokeJobResponse;
import orbag.orchestrator.client.OrchestratorClient;

@Component
public class OrchestrationService implements OrchestratorClient{
		
	@Override
	public InvokeJobResponse invoke(InvokeJobRequest request) {
		return null;
	}

}
