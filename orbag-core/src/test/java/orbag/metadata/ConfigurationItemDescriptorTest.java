package orbag.metadata;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ConfigurationItemDescriptorTest {

	public static class NoConfigurationItem implements Identifiable<Long> {

		@Override
		public Long getIdentifier() {
			return null;
		}
		
	}



	@ConfigurationItem
	public static class WellDefinedConfigurationItem implements Identifiable<Long>, Displayable {

		@Override
		public Long getIdentifier() {
			return null;
		}

		
	}
	
	@Test
	public void testMetadata() {
		assertNull(ConfigurationItemDescriptor.fromClass(NoConfigurationItem.class));
		assertNotNull(ConfigurationItemDescriptor.fromClass(WellDefinedConfigurationItem.class));
	}
}
