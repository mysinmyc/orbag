package orbag.data;

import java.util.Map;

import orbag.metadata.Manageable;
import orbag.reference.ConfigurationItemReferenceService;

public class SerializableRowBuilder implements PartialRow{
	
	ConfigurationItemReferenceService configurationItemReferenceService;
	
	Map<String,Object> fields;
		
	public SerializableRowBuilder(ConfigurationItemReferenceService configurationItemReferenceService,
			Map<String, Object> fields) {
		this.configurationItemReferenceService = configurationItemReferenceService;
		this.fields = fields;
	}

	@Override
	public PartialRow withValue(String columnName, Object value) {
		this.fields.put(columnName, value);
		return this;
	}

	@Override
	public PartialRow withReference(String columnName, Manageable<?> configurationItem) {
		this.fields.put(columnName, configurationItemReferenceService.getReference(configurationItem));
		return this;
	}

}
