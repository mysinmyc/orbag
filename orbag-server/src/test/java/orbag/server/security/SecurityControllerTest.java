package orbag.server.security;

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
public class SecurityControllerTest {

	@LocalServerPort
	Integer localServerPort;
	
	@Autowired
	private TestClients testClients;
	
	@Test
	void testWhoAmI() {
		ResponseEntity<WhoAmIResponse> responseWhoAmiEntity = testClients.testUser1RestTemplate().getForEntity("http://localhost:"+localServerPort+"/api/authentication/whoami", WhoAmIResponse.class);
		assertEquals(HttpStatus.OK,responseWhoAmiEntity.getStatusCode());
		
		WhoAmIResponse response = responseWhoAmiEntity.getBody();
		assertEquals("test1", response.getUserName());
	}

}
