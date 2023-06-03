package orbag.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import orbag.metadata.UnmanagedObjectException;
import orbag.reference.ConfigurationItemReferenceService;

public class SerializableRowBuilder implements PartialRow{
	
	ConfigurationItemReferenceService configurationItemReferenceService;

	SerializableRow row;

	public SerializableRowBuilder(ConfigurationItemReferenceService configurationItemReferenceService, SerializableRow row) {
		this.configurationItemReferenceService = configurationItemReferenceService;
		this.row = row;
	}


	@Override
	public PartialRow withValue(String columnName, Object value) {
		row.getFields().put(columnName, value);
		return this;
	}

	@Override
	public PartialRow withReference(String columnName, Object configurationItem) throws UnmanagedObjectException {
		row.getFields().put(columnName, configurationItemReferenceService.getReference(configurationItem));
		return this;
	}

	@Override
	public PartialRow withTag(String tag) {
		row.getTags().add(tag);
		return this;
	}


}
