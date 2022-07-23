package orbag.util;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MyReflectionUtilsTest {

	public static class TestClass {

		String getName() {
			return null;
		}

		String getWrongGetter(String value) {
			return null;
		}
		
		Boolean isBoolean() {
			return null;
		}

		void setSetter(String value) {

		}
		
		void setWrongSetter(String value, String value2) {

		}
	}

	@Test
	void extractJavaBeanProperties() {

		List<String> getters = new ArrayList<>();
		List<String> setters = new ArrayList<>();

		MyReflectionUtils.forEachDeclaredMethod(TestClass.class, m -> {
			MyReflectionUtils.extractJavaBeanPropertyFromMethodInto(m, (name, setter) -> {
				if (setter) {
					setters.add(name);
				} else {
					getters.add(name);
				}
			});
		});

		assertTrue(getters.contains("name"));
		assertTrue(getters.contains("boolean"));
		assertTrue(setters.contains("setter"));
		
		assertFalse(getters.contains("wrongGetter"));
		assertFalse(getters.contains("wrongSetter"));
	}

}
