package orbag.impl.cis;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Lazy;

import orbag.metadata.ConfigurationItem;

@ConfigurationItem
@Entity
public class ServerGroup extends RootConfigurationItem {
	
	@Lazy
	@OneToMany(mappedBy = "serverGroup")
	List<Server> servers;
	
	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}


}
