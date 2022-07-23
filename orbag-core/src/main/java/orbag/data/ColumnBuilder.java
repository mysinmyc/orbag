package orbag.data;

import java.util.function.Function;

public interface ColumnBuilder<T> {
	
	PartialColumn addColumn(String name, ColumnType columnType);
	
	PartialColumn addGeneratedColumn(String name, ColumnType columnType, Function<T,Object> valueProvider);
}
