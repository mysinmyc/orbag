package orbag.server.metadata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MetadataControllerTest {

	@LocalServerPort
	Integer localServerPort;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testCreateWithDefaultExecutor() {
		ResponseEntity<GetClassModelResponse> response = restTemplate.getForEntity("http://localhost:"+localServerPort+"/api/metadata?properties=true",GetClassModelResponse.class);
		assertEquals(HttpStatus.OK,response.getStatusCode());

	
	}

}
