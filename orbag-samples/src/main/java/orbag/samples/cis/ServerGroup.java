package orbag.samples.cis;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import org.springframework.context.annotation.Lazy;


import orbag.metadata.ConfigurationItem;
import orbag.security.AccessRestricted;
import orbag.samples.security.InfrastructureCisPolicy;

@ConfigurationItem(category = "Infrastructure")
@Entity
@AccessRestricted(inherts = InfrastructureCisPolicy.class)
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
