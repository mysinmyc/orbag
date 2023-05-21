package orbag.samples.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbag.dao.ConfigurationItemDao;
import orbag.data.PaginationInfo;
import orbag.data.TableBuilder;
import orbag.samples.brewery.Brewery;
import orbag.samples.cis.BreweryMenuItem;
import orbag.metadata.DisplayLabel;
import orbag.view.ConfigurationItemViewBase;
import orbag.view.ViewDataBindRequest;
import orbag.visibility.ManagedClasses;

@ManagedClasses(Brewery.class)
@Component
@DisplayLabel("Menu")
public class BreweryMenu extends ConfigurationItemViewBase{

	@Autowired
	ConfigurationItemDao dao;
	
	@Override
	public void bindInto(ViewDataBindRequest request, TableBuilder<Object> tableBuilder) {
		dao.listInto(BreweryMenuItem.class,  tableBuilder.rows()::addRow,new PaginationInfo());
	}

}
