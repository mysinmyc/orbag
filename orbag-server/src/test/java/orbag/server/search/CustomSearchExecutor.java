package orbag.server.search;

import orbag.data.TableBuilder;
import orbag.input.FieldGroupBuilder;
import orbag.input.FieldGroupConsumer;
import orbag.search.SearchContext;
import orbag.search.SearchExecutor;


public class CustomSearchExecutor implements SearchExecutor<Object>{

	@Override
	public void buildSearchParameters(FieldGroupBuilder parametersBuilder, SearchContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeQuery(FieldGroupConsumer parameters, TableBuilder<Object> resultBuilder, SearchContext context) {

	}

}
