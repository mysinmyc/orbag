package orbag.samples.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.data.TableBuilder;
import orbag.samples.cis.Person;
import orbag.samples.cis.Server;
import orbag.metadata.DisplayLabel;
import orbag.search.Operator;
import orbag.search.SearchCondition;
import orbag.view.ConfigurationItemViewBase;
import orbag.view.ViewDataBindRequest;
import orbag.visibility.ManagedClasses;

@ManagedClasses(Person.class)
@Component
@DisplayLabel("Owned servers")
public class PersonOwnedServers extends ConfigurationItemViewBase {

	@Autowired
	ConfigurationItemDao dao;

	@Override
	public boolean isAvailableFor(ViewDataBindRequest request) {
		return true;
	}

	@Override
	public void bindInto(ViewDataBindRequest request, TableBuilder<Object> tableBuilder) {

		dao.searchByConditionsInto(Server.class, tableBuilder.rows()::addRow,
				new SearchCondition<Object>("owner", Operator.EQUAL, request.getCi()));

	}

}
