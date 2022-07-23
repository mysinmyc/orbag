package orbag.data;

import java.util.Collection;

import orbag.util.LimitExceededException;

public interface RowBuilder<T> {

	default PartialRow addRow() throws LimitExceededException {
		return addRow(null);
	}
	
	PartialRow addRow(T item) throws LimitExceededException;
	
	default void addRows(Collection<T> items) throws LimitExceededException {
		for (T item:items) {
			addRow(item);
		}
	}
}
