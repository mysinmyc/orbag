package orbag.visibility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ManagedClassesFilterTest {

	class ParentClass {
	}

	class ChildClass extends ParentClass {
	}

	@ManagedClasses({ParentClass.class, Integer.class})
	class TestedClass {

	}

	@Test
	public void testFilter() {

		ManagedClassesFilter filter = new ManagedClassesFilter();

		assertTrue(filter.isActiveForClass(TestedClass.class, FilterContext.forTargetClass(ParentClass.class)));
		
		assertFalse(filter.isActiveForClass(String.class, FilterContext.forTargetClass(ParentClass.class)));

		assertTrue(filter.isClassVisibile(TestedClass.class, FilterContext.forTargetClass(ParentClass.class)));

		assertTrue(filter.isClassVisibile(TestedClass.class, FilterContext.forTargetClass(ChildClass.class)));
		
		assertTrue(filter.isClassVisibile(TestedClass.class, FilterContext.forTargetClass(Integer.class)));

		assertFalse(filter.isClassVisibile(TestedClass.class, FilterContext.forTargetClass(String.class)));
	}
}
