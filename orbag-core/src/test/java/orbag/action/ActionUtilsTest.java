package orbag.action;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

public class ActionUtilsTest {

	@Test
	public void testAreAllObjectsOfType() {
		assertFalse(ActionUtils.areAllObjectsOfType(Arrays.asList(new Object[]{ new String("ciao"), Integer.valueOf(5)}), Integer.class));
		assertTrue(ActionUtils.areAllObjectsOfType(Arrays.asList(new Object[]{ new String("ciao"),Integer.valueOf(5)}), Object.class));
	}
}
