package orbag.server.create;

import orbag.util.OperationFeedback;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import orbag.input.InputFieldBase;
import orbag.input.StringField;
import orbag.reference.ConfigurationItemReference;
import orbag.server.TestClients;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateControllerTest {


	@LocalServerPort
	Integer localServerPort;
	
	@Autowired
	private TestClients testClients;
	
	@Test
	void testCreateWithDefaultExecutor() {
		ResponseEntity<CreateRequest> responseTemplateEntity = testClients.testUser1RestTemplate().getForEntity("http://localhost:"+localServerPort+"/api/create/template/TestCreateCi",CreateRequest.class);
		assertEquals(HttpStatus.OK,responseTemplateEntity.getStatusCode());
		
		CreateRequest requestTemplate = responseTemplateEntity.getBody();
		assertEquals(1, requestTemplate.getParameters().getFields().size());
		
		InputFieldBase<?> identifier = requestTemplate.getParameters().getField("identifier");
		assertNotNull(identifier);
		assertInstanceOf(StringField.class, identifier);
		
		ResponseEntity<CreateResponse> responseCreateKo = testClients.testUser1RestTemplate().postForEntity("http://localhost:"+localServerPort+"/api/create/execute", requestTemplate, CreateResponse.class);
		assertEquals(HttpStatus.OK,responseCreateKo.getStatusCode());
		assertFalse( responseCreateKo.getBody().getRequestValid() );
		assertNull(responseCreateKo.getBody().getConfigurationItem());

		((StringField)requestTemplate.getParameters().getField("identifier")).setValue("MyIdentifier");
		
		ResponseEntity<CreateResponse> responseCreateOk = testClients.testUser1RestTemplate().postForEntity("http://localhost:"+localServerPort+"/api/create/execute", requestTemplate, CreateResponse.class);
		assertEquals(HttpStatus.OK,responseCreateOk.getStatusCode());
		assertTrue( responseCreateOk.getBody().getRequestValid() );
		ConfigurationItemReference reference = responseCreateOk.getBody().getConfigurationItem();
		assertEquals("MyIdentifier", reference.getIdentifier());
		assertEquals("TestCreateCi", reference.getConfigurationItemType());
	}

}
