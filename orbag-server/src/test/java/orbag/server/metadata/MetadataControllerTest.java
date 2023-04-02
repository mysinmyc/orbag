package orbag.server.metadata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import orbag.server.TestClients;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MetadataControllerTest {

	@LocalServerPort
	Integer localServerPort;
	
	@Autowired
	private TestClients testClients;
	
	@Test
	void testCreateWithDefaultExecutor() {
		ResponseEntity<GetClassModelResponse> response = testClients.testUser1RestTemplate().getForEntity("http://localhost:"+localServerPort+"/api/metadata?properties=true",GetClassModelResponse.class);
		assertEquals(HttpStatus.OK,response.getStatusCode());

	
	}

}
