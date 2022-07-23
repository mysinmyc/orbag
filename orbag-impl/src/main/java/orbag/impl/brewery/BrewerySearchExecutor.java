package orbag.impl.brewery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import orbag.data.TableBuilder;
import orbag.input.FieldGroupBuilder;
import orbag.input.FieldGroupConsumer;
import orbag.search.SearchContext;
import orbag.search.SearchExecutor;
import orbag.util.LimitExceededException;
import orbag.visibility.ManagedClasses;

@ManagedClasses(Brewery.class)
@Component
@Order(0)
public class BrewerySearchExecutor implements SearchExecutor<Brewery>{

	@Autowired
	BreweryClient breweryClient;
	
	@Override
	public void buildSearchParameters(FieldGroupBuilder parametersBuilder, SearchContext context) {
		parametersBuilder.addStringField("name", "Brewery name pattern");
	}

	@Override
	public void executeQuery(FieldGroupConsumer parameters, TableBuilder<Brewery> resultBuilder, SearchContext context)
			throws LimitExceededException {
		List<Brewery> result;
		if (parameters.isFilled("name")) {
			result = breweryClient.searchByName(parameters.getValue("name"));
		} else {
			result = breweryClient.list();
		}
		resultBuilder.rows().addRows(result);
	}

}
