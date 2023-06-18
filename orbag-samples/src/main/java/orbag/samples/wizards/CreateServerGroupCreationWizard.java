package orbag.samples.wizards;

import orbag.create.AnnotatedInputConfigurationItemCreationWizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import orbag.create.CreationContext;
import orbag.dao.ConfigurationItemDao;
import orbag.samples.cis.Server;
import orbag.samples.cis.ServerGroup;
import orbag.input.FieldGroupConsumer;
import orbag.input.Input;
import orbag.input.InputField;
import orbag.visibility.ManagedClasses;

@ManagedClasses(ServerGroup.class)
@Component
@Order(0)
@Input({
	@InputField(name="groupName", displayLabel="Group Name", type = String.class),
	@InputField(name="serverPrefix", displayLabel="Server Prefix", type = String.class),
	@InputField(name="servers", displayLabel="Number of Servers", type = Integer.class, defaultValue="5"),
})
public class CreateServerGroupCreationWizard implements AnnotatedInputConfigurationItemCreationWizard {

	@Autowired
	ConfigurationItemDao dao;

	@Override
	public Object create(FieldGroupConsumer parameters, CreationContext context) {
		ServerGroup serverGroup = new ServerGroup();
		serverGroup.setName(parameters.getValue("groupName"));
		dao.create(serverGroup);
		for (int cnt=0;cnt<(Integer)parameters.getValue("servers");cnt++) {
			Server server = new Server();
			server.setName(parameters.getValue("serverPrefix")+""+cnt);
			server.setServerGroup(serverGroup);
			dao.create(server);
		}
		return serverGroup;
	}

}
