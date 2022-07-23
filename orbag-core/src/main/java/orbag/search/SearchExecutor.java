package orbag.search;

import orbag.data.ColumnBuilder;
import orbag.data.DataUtils;
import orbag.data.TableBuilder;
import orbag.input.FieldGroupBuilder;
import orbag.input.FieldGroupConsumer;

public interface SearchExecutor<T> {

	void buildSearchParameters(FieldGroupBuilder parametersBuilder, SearchContext context);

	default void buildResultColumns(ColumnBuilder<T> columnBuilder, ResultType resultType, SearchContext context) {
		DataUtils.buildColumnsFromConfigurationItemDescriptor(context.getConfigurationItemDescriptor(), columnBuilder,
				p -> {
					switch (resultType) {
					case HIGHLIGHTED_FIELDS:
						return p.isHighlighted();
					case ALL_FIELDS:
						return true;
					default:
						return false;
					}
				});
	}

	void executeQuery(FieldGroupConsumer parameters, TableBuilder<T> resultBuilder, SearchContext context);
}
