package orbag.data;

import orbag.metadata.UnmanagedObjectException;

public interface PartialRow {
	
	PartialRow withValue(String columnName, Object value);
	
	PartialRow withReference(String columnName, Object configurationItem) throws UnmanagedObjectException;
}
