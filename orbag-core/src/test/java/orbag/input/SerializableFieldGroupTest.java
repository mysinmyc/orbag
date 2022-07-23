package orbag.input;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

public class SerializableFieldGroupTest {

	
	@Test
	public void testDuplicatesFields() throws DuplicateFieldException {
		
		SerializableFieldGroup serializableFieldGroup = new SerializableFieldGroup();
		
		serializableFieldGroup.addStringField("ciao", "xxxx");
		
		serializableFieldGroup.addStringField("miao", "xxxx");
		
		assertThrowsExactly(DuplicateFieldException.class, ()-> {
			serializableFieldGroup.addStringField("ciao", "aaaa");
		});
		
	}
}
