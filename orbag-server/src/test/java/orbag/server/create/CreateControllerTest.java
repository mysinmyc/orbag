package orbag.server.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import orbag.input.InputFieldBase;
import orbag.input.StringField;
import orbag.reference.ConfigurationItemReferenceExt;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateControllerTest {

	@LocalServerPort
	Integer localServerPort;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testCreateWithDefaultExecutor() {
		ResponseEntity<CreateRequest> responseTemplateEntity = restTemplate.getForEntity("http://localhost:"+localServerPort+"/api/create/template/TestCreateCi",CreateRequest.class);
		assertEquals(HttpStatus.OK,responseTemplateEntity.getStatusCode());
		
		CreateRequest requestTemplate = responseTemplateEntity.getBody();
		assertEquals(1, requestTemplate.getParameters().getFields().size());
		
		InputFieldBase<?> identifier = requestTemplate.getParameters().getField("identifier");
		assertNotNull(identifier);
		assertInstanceOf(StringField.class, identifier);
		
		ResponseEntity<ConfigurationItemReferenceExt> responseCreateKo = restTemplate.postForEntity("http://localhost:"+localServerPort+"/api/create/execute", requestTemplate, ConfigurationItemReferenceExt.class);
		assertNotEquals(HttpStatus.OK,responseCreateKo.getStatusCode());
			
		((StringField)requestTemplate.getParameters().getField("identifier")).setValue("MyIdentifier");
		
		ResponseEntity<ConfigurationItemReferenceExt> responseCreateOk = restTemplate.postForEntity("http://localhost:"+localServerPort+"/api/create/execute", requestTemplate, ConfigurationItemReferenceExt.class);
		assertEquals(HttpStatus.OK,responseCreateOk.getStatusCode());
		ConfigurationItemReferenceExt reference = responseCreateOk.getBody();
		assertEquals("MyIdentifier", reference.getIdentifier());
		assertEquals("TestCi", reference.getConfigurationItemType());
	}

}
