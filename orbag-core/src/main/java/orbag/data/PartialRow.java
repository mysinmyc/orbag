package orbag.data;

import orbag.metadata.Manageable;

public interface PartialRow {
	
	PartialRow withValue(String columnName, Object value);
	
	PartialRow withReference(String columnName, Manageable<?> configurationItem);
}
