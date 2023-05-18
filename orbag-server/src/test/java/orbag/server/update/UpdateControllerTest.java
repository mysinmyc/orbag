package orbag.server.update;

import orbag.dao.ConfigurationItemNotFoundException;
import orbag.metadata.UnmanagedObjectException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import orbag.dao.ConfigurationItemDao;
import orbag.input.BooleanField;
import orbag.input.EnumField;
import orbag.input.InputFieldBase;
import orbag.input.NumericField;
import orbag.input.StringField;
import orbag.reference.ConfigurationItemReference;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.server.TestClients;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOError;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UpdateControllerTest {

	@LocalServerPort
	Integer localServerPort;
	
	@Autowired
	private TestClients testClients;
	
	@Test
	void testUpdate(@Autowired ConfigurationItemDao dao, @Autowired ConfigurationItemReferenceService configurationItemReferenceService) throws UnmanagedObjectException, ConfigurationItemNotFoundException {
		
		TestUpdateCi testUpdateCi = new TestUpdateCi();
		testUpdateCi.setIdentifier("ciao");
		dao.create(testUpdateCi);
		
		ConfigurationItemReference configurationItemReference = configurationItemReferenceService.getReference(testUpdateCi);


		ResponseEntity<UpdateRequest> responseTemplateEntity = testClients.testUser1RestTemplate().postForEntity("http://localhost:"+localServerPort+"/api/update/buildTemplate",configurationItemReference,UpdateRequest.class);
		assertEquals(HttpStatus.OK,responseTemplateEntity.getStatusCode());
		
		UpdateRequest requestTemplate = responseTemplateEntity.getBody();
		assertEquals(6, requestTemplate.getProperties().getFields().size());
		
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

		InputFieldBase<?> enumProperty = requestTemplate.getProperties().getField("enumProperty");
		assertNotNull(enumProperty);
		assertInstanceOf(EnumField.class, enumProperty);
		((EnumField)enumProperty).setValue("VALUEA");
		enumProperty.setChanged(true);		
		
		InputFieldBase<?> readOnlyProperty = requestTemplate.getProperties().getField("readOnlyProperty");
		assertNotNull(readOnlyProperty);
		assertTrue(readOnlyProperty.isReadOnly());
		
		ResponseEntity<ConfigurationItemReference> responseUpdateOk = testClients.testUser1RestTemplate().postForEntity("http://localhost:"+localServerPort+"/api/update/execute", requestTemplate, ConfigurationItemReference.class);
		assertEquals(HttpStatus.OK,responseUpdateOk.getStatusCode());
			
		TestUpdateCi afterUpdate = (TestUpdateCi) dao.getExistingCiOrThrow(responseUpdateOk.getBody());
		
		assertEquals("ciao",afterUpdate.getStringProperty());
		assertEquals(5,afterUpdate.getIntegerProperty());
		assertEquals(true,afterUpdate.getBooleanProperty());
		assertEquals(MyEnum.VALUEA,afterUpdate.getEnumProperty());
				
		((StringField)stringProperty).setValue("ciao");
		readOnlyProperty.setChanged(true);		
		
		ResponseEntity<ConfigurationItemReference> responseUpdateKo = testClients.testUser1RestTemplate().postForEntity("http://localhost:"+localServerPort+"/api/update/execute", requestTemplate, ConfigurationItemReference.class);
		assertNotEquals(HttpStatus.OK,responseUpdateKo.getStatusCode());
	
		
	}
	
	
	@Test
	void testUpdateSecurity(@Autowired ConfigurationItemDao dao, @Autowired ConfigurationItemReferenceService configurationItemReferenceService) throws UnmanagedObjectException {
		
		TestUpdateCi testUpdateCi = new TestUpdateCi();
		testUpdateCi.setIdentifier("testSercurity");
		dao.create(testUpdateCi );
		
		ConfigurationItemReference configurationItemReference = configurationItemReferenceService.getReference(testUpdateCi);

		ResponseEntity<UpdateRequest> responseOkTemplateEntity = testClients.testUser1RestTemplate().postForEntity("http://localhost:"+localServerPort+"/api/update/buildTemplate",configurationItemReference,UpdateRequest.class);
		assertEquals(HttpStatus.OK,responseOkTemplateEntity.getStatusCode());

		UpdateRequest requestTemplate = responseOkTemplateEntity.getBody();
		InputFieldBase<?> stringProperty = requestTemplate.getProperties().getField("stringProperty");
		assertNotNull(stringProperty);
		assertInstanceOf(StringField.class, stringProperty);
		((StringField)stringProperty).setValue("ciao");
		stringProperty.setChanged(true);

		ResponseEntity<ConfigurationItemReference> responseOkUpdatentity = testClients.testUser1RestTemplate().postForEntity("http://localhost:"+localServerPort+"/api/update/execute", requestTemplate, ConfigurationItemReference.class);
		assertEquals(HttpStatus.OK,responseOkUpdatentity.getStatusCode());

		assertThrows(ResourceAccessException.class,()-> testClients.testUser2RestTemplate().postForEntity("http://localhost:"+localServerPort+"/api/update/execute",requestTemplate, ConfigurationItemReference.class));


		assertThrows(ResourceAccessException.class,()-> testClients.testUser3RestTemplate().postForEntity("http://localhost:"+localServerPort+"/api/update/buildTemplate", responseOkUpdatentity.getBody(),UpdateRequest.class));
		assertThrows(ResourceAccessException.class,()-> testClients.testUser3RestTemplate().postForEntity("http://localhost:"+localServerPort+"/api/update/execute", requestTemplate, ConfigurationItemReference.class));

	}

}
