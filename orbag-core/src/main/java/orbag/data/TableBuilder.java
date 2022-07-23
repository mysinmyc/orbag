package orbag.data;


public interface TableBuilder<T> extends ColumnBuilder<T> {

	RowBuilder<T> rows();
}
