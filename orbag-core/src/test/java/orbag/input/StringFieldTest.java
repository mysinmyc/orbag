package orbag.input;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StringFieldTest {

	@Test
	public void testStringField() {
				
		for (String currentValue : new String[] {"",null, "  "}) {
			StringField stringField = new StringField();
			stringField.setValue(currentValue);
			assertTrue(stringField.isEmpty());
		}
				
		StringField stringField = new StringField();
		stringField.setValue("ciao");
		assertFalse(stringField.isEmpty());
		
	}
}
