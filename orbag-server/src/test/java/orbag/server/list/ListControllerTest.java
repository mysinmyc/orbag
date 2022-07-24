package orbag.server.list;

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
public class ListControllerTest {

	@LocalServerPort
	Integer localServerPort;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testList() {
		ResponseEntity<ListConfigurationItemResponse> responseListEntity = restTemplate.getForEntity("http://localhost:"+localServerPort+"/api/list/TestListCi?limit=10",ListConfigurationItemResponse.class);
		assertEquals(HttpStatus.OK,responseListEntity.getStatusCode());

		assertEquals(10, responseListEntity.getBody().getCis().size());
	}

}
