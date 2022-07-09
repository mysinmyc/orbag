package orbag.metadata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class MetadataRegistryTest {

	@ConfigurationItem()
	static class ParentConfigurationItem implements Manageable{

		@Override
		public Long getIdentifier() {
			return null;
		}
		
	}
	
	static class ChildConfigurationItem  extends ParentConfigurationItem {
		
	}
	
	@Test
	public void testDescriptor() {
		
		
		MetadataRegistry metadataRegistry = new MetadataRegistry();
		metadataRegistry.setManagedClasses(ParentConfigurationItem.class);
		
		assertNotNull(metadataRegistry.getConfigurationItemDescriptorByClass(ParentConfigurationItem.class));
		
		ConfigurationItemDescriptor configurationItemDescriptor = metadataRegistry.getConfigurationItemDescriptorByClass(ChildConfigurationItem.class);
		assertNotNull(configurationItemDescriptor);
		assertEquals(configurationItemDescriptor.getJavaClass(), ParentConfigurationItem.class);
		
	}
}
