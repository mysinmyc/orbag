package orbag.server.update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import orbag.dao.ConfigurationItemDao;
import orbag.input.BooleanField;
import orbag.input.InputFieldBase;
import orbag.input.NumericField;
import orbag.input.StringField;
import orbag.reference.ConfigurationItemReferenceExt;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UpdateControllerTest {

	@LocalServerPort
	Integer localServerPort;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testUpdate(@Autowired ConfigurationItemDao dao) {
		
		TestUpdateCi testUpdateCi = new TestUpdateCi();
		testUpdateCi.setIdentifier("ciao");
		dao.create(testUpdateCi );
		
		ResponseEntity<UpdateRequest> responseTemplateEntity = restTemplate.getForEntity("http://localhost:"+localServerPort+"/api/update/template/TestUpdateCi/"+testUpdateCi.getIdentifier(),UpdateRequest.class);
		assertEquals(HttpStatus.OK,responseTemplateEntity.getStatusCode());
		
		UpdateRequest requestTemplate = responseTemplateEntity.getBody();
		assertEquals(5, requestTemplate.getProperties().getFields().size());
		
		InputFieldBase<?> stringProperty = requestTemplate.getProperties().getField("stringProperty");
		assertNotNull(stringProperty);
		assertInstanceOf(StringField.class, stringProperty);
		((StringField)stringProperty).setValue("ciao");
		stringProperty.setChanged(true);
		
		InputFieldBase<?> integerProperty = requestTemplate.getProperties().getField("integerProperty");
		assertNotNull(integerProperty);
		assertInstanceOf(NumericField.class, integerProperty);
		((NumericField)integerProperty).setValue(5);
		integerProperty.setChanged(true);
				
		InputFieldBase<?> booleanProperty = requestTemplate.getProperties().getField("booleanProperty");
		assertNotNull(booleanProperty);
		assertInstanceOf(BooleanField.class, booleanProperty);
		((BooleanField)booleanProperty).setValue(true);
		booleanProperty.setChanged(true);
		
		InputFieldBase<?> readOnlyProperty = requestTemplate.getProperties().getField("readOnlyProperty");
		assertNotNull(readOnlyProperty);
		assertTrue(readOnlyProperty.isReadOnly());
		
		ResponseEntity<ConfigurationItemReferenceExt> responseUpdateOk = restTemplate.postForEntity("http://localhost:"+localServerPort+"/api/update/execute", requestTemplate, ConfigurationItemReferenceExt.class);
		assertEquals(HttpStatus.OK,responseUpdateOk.getStatusCode());
			
		TestUpdateCi afterUpdate = (TestUpdateCi) dao.getCi(responseUpdateOk.getBody());
		
		assertEquals("ciao",afterUpdate.getStringProperty());
		assertEquals(5,afterUpdate.getIntegerProperty());
		assertEquals(true,afterUpdate.getBooleanProperty());
				
		((StringField)stringProperty).setValue("ciao");
		readOnlyProperty.setChanged(true);		
		
		ResponseEntity<ConfigurationItemReferenceExt> responseUpdateKo = restTemplate.postForEntity("http://localhost:"+localServerPort+"/api/update/execute", requestTemplate, ConfigurationItemReferenceExt.class);
		assertNotEquals(HttpStatus.OK,responseUpdateKo.getStatusCode());
	
		
	}

}
