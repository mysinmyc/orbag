package orbag.server.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import orbag.reference.ConfigurationItemReferenceService;
import orbag.server.TestClients;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ViewControllerTest {

	@LocalServerPort
	Integer localServerPort;

	@Autowired
	private TestClients testClients;

	@Test
	void testView(@Autowired ConfigurationItemReferenceService configurationItemReferenceService) {

		TestViewCi ci = new TestViewCi();
		ci.setIdentifier("ciao");
		GetAvailableViewsRequest request = new GetAvailableViewsRequest();

		request.setTargetCi(configurationItemReferenceService.getReference(ci));
		
		ResponseEntity<GetAvailableViewsResponse> responseGetAvailable = testClients.testUser1RestTemplate().postForEntity(
				"http://localhost:" + localServerPort + "/api/view/getAvailable", request,
				GetAvailableViewsResponse.class);
		assertEquals(HttpStatus.OK, responseGetAvailable.getStatusCode());

		SerializableView view = responseGetAvailable.getBody().getAvailableViews().stream()
				.filter(a -> a.getIdentifier().equals("testView")).findFirst().orElse(null);
		assertNotNull(view);
		
		BindViewRequest bindViewRequest = new BindViewRequest();
		bindViewRequest.setView(view);
		bindViewRequest.setTargetCi(request.getTargetCi());
		
		ResponseEntity<BindViewResponse> bindResponseEntity = testClients.testUser1RestTemplate().postForEntity("http://localhost:" + localServerPort + "/api/view/bind", bindViewRequest,
				BindViewResponse.class);
		assertEquals(HttpStatus.OK, bindResponseEntity.getStatusCode());

		
	}

}
