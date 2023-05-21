package orbag.samples.views;

import org.springframework.stereotype.Component;

import orbag.data.RowBuilder;
import orbag.data.TableBuilder;
import orbag.samples.cis.Server;
import orbag.samples.cis.ServerGroup;
import orbag.metadata.DisplayLabel;
import orbag.view.ConfigurationItemViewBase;
import orbag.view.ViewDataBindRequest;
import orbag.visibility.ManagedClasses;

@ManagedClasses(ServerGroup.class)
@Component
@DisplayLabel("Servers")
public class ServerGroupServersView extends ConfigurationItemViewBase{

	
	@Override
	public boolean isAvailableFor(ViewDataBindRequest request) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bindInto(ViewDataBindRequest request,TableBuilder<Object> tableBuilder) {
		ServerGroup serverGroup= ((ServerGroup) request.getCi());
		@SuppressWarnings("rawtypes")
		RowBuilder rowBuilder = tableBuilder.rows();
		for (Server server : serverGroup.getServers()) {
			rowBuilder.addRow(server);
		}
	}

}
