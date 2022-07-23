package orbag.metadata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ConfigurationItemDescriptorTest2 {

	@ConfigurationItem
	static class ConfigurationItemClass implements Manageable<Integer> {
	
		String prova;

		public String getProva() {
			return prova;
		}

		@ConfigurationItemProperty
		public void setProva(String prova) {
			this.prova = prova;
		}

		@Override
		public Integer getIdentifier() {
			return null;
		}
	}
	
	@Test
	void test() {
		
		ConfigurationItemDescriptor nullDescriptor = ConfigurationItemDescriptor.fromClass(String.class);
		assertNull(nullDescriptor);
		
		ConfigurationItemDescriptor descriptor = ConfigurationItemDescriptor.fromClass(ConfigurationItemClass.class);
		assertNotNull(descriptor);
		
		assertEquals(1,  descriptor.getProperties().size());
		
		ConfigurationItemPropertyDescriptor prova = descriptor.getProperty("prova");
		assertNotNull(prova);
		assertEquals("prova", prova.getName());
		assertNotNull(prova.getGetterMethod());
	}

}
