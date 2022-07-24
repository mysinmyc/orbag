package orbag.server.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import orbag.reference.ConfigurationItemReferenceService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ActionControllerTest {

	@LocalServerPort
	Integer localServerPort;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testCreateWithDefaultExecutor(@Autowired ConfigurationItemReferenceService configurationItemReferenceService) {

		TestActionCi ci = new TestActionCi();
		ci.setIdentifier("ciao");
		GetAvailableActionsRequest request = new GetAvailableActionsRequest();

		request.setTargetCis(Arrays.asList(configurationItemReferenceService.getReference(ci)));
		ResponseEntity<GetAvailableActionsResponse> responseGetAvailable = restTemplate.postForEntity(
				"http://localhost:" + localServerPort + "/api/action/getAvailable", request,
				GetAvailableActionsResponse.class);
		assertEquals(HttpStatus.OK, responseGetAvailable.getStatusCode());

		SerializableAction action = responseGetAvailable.getBody().getAvailableActions().stream()
				.filter(a -> a.getIdentifier().equals("testAction")).findFirst().orElse(null);
		assertNotNull(action);
		
		SubmitActionRequest submitActionRequest = new SubmitActionRequest();
		submitActionRequest.setAction(action);
		submitActionRequest.setTargetCis(request.getTargetCis());
		
		ResponseEntity<SubmitActionResponse> submResponseEntity = restTemplate.postForEntity("http://localhost:" + localServerPort + "/api/action/submit", submitActionRequest,
				SubmitActionResponse.class);
		assertEquals(HttpStatus.OK, submResponseEntity.getStatusCode());

		
	}

}
