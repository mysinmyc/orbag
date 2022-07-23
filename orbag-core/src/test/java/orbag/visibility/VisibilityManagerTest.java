package orbag.visibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import orbag.EnableOrbagCore;

@SpringBootTest
@EnableOrbagCore
public class VisibilityManagerTest {

	static class TestedClass {
		
	}
	
	static class VisibleClass extends TestedClass {
		
	}
	
	@Configuration
	static class ConfigurationClass {
		
		@Bean
		ClassVisibilityFilter classVisibilityFilterWithoutTarget() {
			return new ClassVisibilityFilter() {
				
				@Override
				public boolean isClassVisibile(Class<?> classToTest, FilterContext context) {
					return VisibleClass.class.isAssignableFrom(classToTest);
				}
				
				@Override
				public boolean isActiveForClass(Class<?> classToTest, FilterContext context) {
					return TestedClass.class.isAssignableFrom(classToTest);
				}
			};
		}
		
		@Bean
		ObjectVisibilityFilter objectVisibilityFilterWithoutTarget() {
			return new ObjectVisibilityFilter() {
				
				@Override
				public boolean isObjectVisibile(Object objectToTest, FilterContext request) {
					return ((String)objectToTest).contains("visible") ;
				}
				
				@Override
				public boolean isActiveForObject(Object objectToTest, FilterContext request) {
					return request.getTargetObject() ==null && objectToTest instanceof String;
				}
			};
		}
	}
	
	@Test
	void testObjectsWithoutTarget(@Autowired VisibilityManager visibilityManager) {
		
		assertEquals("visible", visibilityManager.findFirstObject(Arrays.asList("pippo","pluto","paperino","visible"), FilterContext.withoutTarget()));
		
		assertNull(visibilityManager.findFirstObject(Arrays.asList(1,2,3), FilterContext.withoutTarget()));
		
		assertInstanceOf(VisibleClass.class, visibilityManager.findFirstObject(Arrays.asList(new TestedClass(),new VisibleClass()), FilterContext.withoutTarget()));
	}
	
	@Test
	void testClassesWithoutTarget(@Autowired VisibilityManager visibilityManager) {

		List<Class<?>> classes=Arrays.asList(TestedClass.class,VisibleClass.class);
		assertEquals(VisibleClass.class, visibilityManager.findFirstClass(classes, FilterContext.withoutTarget()));
	}

}
