package orbag.server.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import orbag.server.EnableOrbagServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import orbag.server.TestClients;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ListControllerTest {

	@LocalServerPort
	Integer localServerPort;
	
	@Autowired
	private TestClients testClients;
	
	@Test
	void testList() {
		ResponseEntity<ListConfigurationItemResponse> responseListEntity = testClients.testUser1RestTemplate().getForEntity("http://localhost:"+localServerPort+"/api/list/TestListCi?limit=10",ListConfigurationItemResponse.class);
		assertEquals(HttpStatus.OK,responseListEntity.getStatusCode());

		assertEquals(10, responseListEntity.getBody().getCis().size());
	}

}
