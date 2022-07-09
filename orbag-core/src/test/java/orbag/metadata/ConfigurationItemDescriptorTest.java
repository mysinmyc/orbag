package orbag.metadata;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ConfigurationItemDescriptorTest {

	public static class NoConfigurationItem implements Identifiable {

		@Override
		public Long getIdentifier() {
			return null;
		}
		
	}

	@ConfigurationItem
	public static class NoConfigurationItemB {
		
	}

	@ConfigurationItem
	public static class WellDefinedConfigurationItem implements Manageable {

		@Override
		public Long getIdentifier() {
			return null;
		}

		
	}
	
	@Test
	public void testMetadata() {
		assertNull(ConfigurationItemDescriptor.fromClass(NoConfigurationItem.class));
		assertNull(ConfigurationItemDescriptor.fromClass(NoConfigurationItemB.class));
		assertNotNull(ConfigurationItemDescriptor.fromClass(WellDefinedConfigurationItem.class));
	}
}
