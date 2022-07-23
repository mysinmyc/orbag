package orbag.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.data.RowBuilder;
import orbag.data.TableBuilder;
import orbag.input.FieldGroupBuilder;
import orbag.input.FieldGroupConsumer;
import orbag.util.LimitExceededException;
import orbag.visibility.ManagedClasses;

@ManagedClasses(Object.class)
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultSearchExecutor implements SearchExecutor<Object> {

	@Autowired
	SearchUtils searchUtils;

	@Autowired
	ConfigurationItemDao dao;

	@Override
	public void buildSearchParameters(FieldGroupBuilder parametersBuilder, SearchContext context) {
		searchUtils.buildSearcheableFields(context.getConfigurationItemDescriptor().getJavaClass(), parametersBuilder);
	}

	@Override
	public void executeQuery(FieldGroupConsumer parameters, TableBuilder<Object> resultBuilder, SearchContext context)
			throws LimitExceededException {
		RowBuilder<Object> rowsBuilder = resultBuilder.rows();
		if (parameters.isEmpty()) {
			dao.listInto(context.getConfigurationItemDescriptor().getJavaClass(), rowsBuilder::addRow,
					context.getPaginationInfo());
		} else {
			SearchConditions searchConditions = searchUtils.fromFields(parameters, false);
			dao.searchByConditionsInto(context.getConfigurationItemDescriptor().getJavaClass(), searchConditions,
					rowsBuilder::addRow, context.getPaginationInfo());
		}

	}

}
