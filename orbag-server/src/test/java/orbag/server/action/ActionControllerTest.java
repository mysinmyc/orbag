package orbag.server.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import orbag.dao.ConfigurationItemDao;
import orbag.metadata.UnmanagedObjectException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import orbag.action.ActionConsequences;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.server.TestClients;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ActionControllerTest {

	@LocalServerPort
	Integer localServerPort;

	@Autowired
	ConfigurationItemDao dao;

	@Autowired
	private TestClients testClients;

	@Test
	void testCreateWithDefaultExecutor(@Autowired ConfigurationItemReferenceService configurationItemReferenceService) throws UnmanagedObjectException {

		TestActionCi ci = new TestActionCi();
		ci.setIdentifier("ciao");

		GetAvailableActionsRequest request = new GetAvailableActionsRequest();

		request.setTargetCis(Arrays.asList(configurationItemReferenceService.getReference(ci)));
		ResponseEntity<GetAvailableActionsResponse> responseGetAvailable = testClients.testUser1RestTemplate().postForEntity(
				"http://localhost:" + localServerPort + "/api/action/buildAvailableList", request,
				GetAvailableActionsResponse.class);
		assertEquals(HttpStatus.OK, responseGetAvailable.getStatusCode());

		SerializableAction action = responseGetAvailable.getBody().getAvailableActions().stream()
				.filter(a -> a.getIdentifier().equals("testAction")).findFirst().orElse(null);
		assertNotNull(action);
		
		assertEquals("Action on label ciao",action.getDisplayLabel());

		BuildActionTemplateRequest buildTemplateRequest = new BuildActionTemplateRequest();
		buildTemplateRequest.setAction(action);
		buildTemplateRequest.setTargetCis(request.getTargetCis());
		
		ResponseEntity<SubmitActionRequest> responseBuildTemplateEntity = testClients.testUser1RestTemplate().postForEntity(
				"http://localhost:" + localServerPort + "/api/action/buildTemplate", buildTemplateRequest,
				SubmitActionRequest.class);
		
		assertEquals(HttpStatus.OK, responseBuildTemplateEntity.getStatusCode());
		SubmitActionRequest submitActionRequest = responseBuildTemplateEntity.getBody();
		
		
		ResponseEntity<SubmitActionResponse> submitActionResponseEntity = testClients.testUser1RestTemplate().postForEntity("http://localhost:" + localServerPort + "/api/action/submit", submitActionRequest,
				SubmitActionResponse.class);
		assertEquals(HttpStatus.OK, submitActionResponseEntity.getStatusCode());

		SubmitActionResponse response = submitActionResponseEntity.getBody();
		assertEquals(ActionConsequences.UPDATED, response.getConsequences());
		
	}

}
