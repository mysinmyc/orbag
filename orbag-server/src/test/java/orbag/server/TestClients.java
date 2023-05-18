package orbag.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class TestClients {

	Map<String, TestRestTemplate> restTemplates = new HashMap<>();

	@Autowired
	TestRestTemplate restTemplate;

	public TestRestTemplate getOrCreate(String user, Supplier<String> password) {
		TestRestTemplate result = restTemplates.get(user);
		if (result ==null)  {
			result= new TestRestTemplate(new RestTemplateBuilder()).withBasicAuth(user,password.get());
			result.setUriTemplateHandler(restTemplate.getRestTemplate().getUriTemplateHandler());
			restTemplates.put(user,result);
		}

		return result;
	}

	@Autowired
	TestRestTemplate testRestTemplate;
	
	
	public TestRestTemplate testUser1RestTemplate() {
		return getOrCreate("test1",()-> "test1");
	}
	
	public TestRestTemplate unauthenticatedRestTemplate() {
		return testRestTemplate;
	}

	public TestRestTemplate testUser2RestTemplate() {
		return getOrCreate("test2",()-> "test2");
	}
	
	public TestRestTemplate testUser3RestTemplate() {
		return getOrCreate("test3",()-> "test3");
	}
}
