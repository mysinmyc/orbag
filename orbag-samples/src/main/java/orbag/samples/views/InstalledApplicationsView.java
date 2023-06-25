package orbag.samples.views;

import orbag.data.RowBuilder;
import orbag.data.TableBuilder;
import orbag.metadata.DisplayLabel;
import orbag.samples.cis.ApplicationInstance;
import orbag.samples.cis.BusinessApplication;
import orbag.samples.cis.Server;
import orbag.samples.cis.ServerGroup;
import orbag.view.ConfigurationItemViewBase;
import orbag.view.ViewDataBindRequest;
import orbag.visibility.ManagedClasses;
import org.springframework.stereotype.Component;

@ManagedClasses(Server.class)
@Component
@DisplayLabel("Installed applications")
public class InstalledApplicationsView extends ConfigurationItemViewBase{

	
	@Override
	public boolean isAvailableFor(ViewDataBindRequest request) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bindInto(ViewDataBindRequest request,TableBuilder<Object> tableBuilder) {
		Server server= ((Server) request.getCi());
		@SuppressWarnings("rawtypes")
		RowBuilder rowBuilder = tableBuilder.rows();
		for (ApplicationInstance applicationInstance : server.getInstalledApplications()) {
			rowBuilder.addRow(applicationInstance);
		}
	}

}
