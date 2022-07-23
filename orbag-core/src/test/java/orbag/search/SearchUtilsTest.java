package orbag.search;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import orbag.EnableOrbagCore;
import orbag.input.BooleanField;
import orbag.input.NumericField;
import orbag.input.SerializableFieldGroup;
import orbag.input.StringField;

@SpringBootTest
@SpringBootConfiguration
@EnableOrbagCore
public class SearchUtilsTest {

	class Parent {
		
		@Searcheable
		String searcheableFieldParent;
	}
	
	class Child extends Parent{
		
		@Searcheable
		String searcheableFieldChild;
		
		@Searcheable 
		String stringField;
			
		@Searcheable 
		Boolean booleanField;
		
		@Searcheable
		Integer integerField;
		
		String anotherField;
	}
	
	@Test
	public void testFields(@Autowired SearchUtils searchUtils) {
		
		SerializableFieldGroup fieldGroup = new SerializableFieldGroup();
		searchUtils.buildSearcheableFields(Child.class, fieldGroup);
		
		assertNotNull(fieldGroup.getField("searcheableFieldParent"));
		assertNotNull(fieldGroup.getField("searcheableFieldChild"));
		assertNull(fieldGroup.getField("anotherField"));
		
		
		assertInstanceOf(NumericField.class, fieldGroup.getField("integerField"));
		assertInstanceOf(StringField.class, fieldGroup.getField("stringField"));
		assertInstanceOf(BooleanField.class, fieldGroup.getField("booleanField"));
		
	}
}
