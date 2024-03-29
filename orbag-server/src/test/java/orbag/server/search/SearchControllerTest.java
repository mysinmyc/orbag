package orbag.server.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import orbag.data.SerializableTable;
import orbag.input.BooleanField;
import orbag.input.InputFieldBase;
import orbag.input.NumericField;
import orbag.input.StringField;
import orbag.server.TestClients;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SearchControllerTest {

	@LocalServerPort
	Integer localServerPort;
	
	@Autowired
	private TestClients testClients;
	
	@Test
	void testSearchWithDefaultExecutor() {
		ResponseEntity<SearchRequest> responseTemplateEntity = testClients.testUser1RestTemplate().getForEntity("http://localhost:"+localServerPort+"/api/search/template/TestSearchCi",SearchRequest.class);
		assertEquals(HttpStatus.OK,responseTemplateEntity.getStatusCode());
		
		SearchRequest requestTemplate = responseTemplateEntity.getBody();
		assertEquals(3, requestTemplate.getParameters().getFields().size());
		
		InputFieldBase<?> stringSearcheableField = requestTemplate.getParameters().getField("stringSearcheableField");
		assertNotNull(stringSearcheableField);
		assertInstanceOf(StringField.class, stringSearcheableField);
		
		InputFieldBase<?> numericSearcheableField = requestTemplate.getParameters().getField("numericSearcheableField");
		assertNotNull(numericSearcheableField);
		assertInstanceOf(NumericField.class, numericSearcheableField);
		
		InputFieldBase<?> booleanSearcheableField = requestTemplate.getParameters().getField("booleanSearcheableField");
		assertNotNull(booleanSearcheableField);
		assertInstanceOf(BooleanField.class, booleanSearcheableField);
		
		((StringField)requestTemplate.getParameters().getField("stringSearcheableField")).setValue("test");
		((NumericField)requestTemplate.getParameters().getField("numericSearcheableField")).setValue(7);
		ResponseEntity<SerializableTable> responseSearchEntity = testClients.testUser1RestTemplate().postForEntity("http://localhost:"+localServerPort+"/api/search/execute", requestTemplate, SerializableTable.class);
		assertEquals(HttpStatus.OK,responseSearchEntity.getStatusCode());
			
		SerializableTable resultTable = responseSearchEntity.getBody();
		assertEquals(10,resultTable.getRows().size());
	}

}
