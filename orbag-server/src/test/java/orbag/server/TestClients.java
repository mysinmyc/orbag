package orbag.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestClients {

	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	
	public TestRestTemplate testUser1RestTemplate() {
		return testRestTemplate.withBasicAuth("test1", "test1");
	}
	
	public TestRestTemplate unauthenticatedRestTemplate() {
		return testRestTemplate;
	}

	public TestRestTemplate testUser2RestTemplate() {
		return testRestTemplate.withBasicAuth("test2", "test2");
	}
	
	public TestRestTemplate testUser3RestTemplate() {
		return testRestTemplate.withBasicAuth("test3", "test3");
	}
}
