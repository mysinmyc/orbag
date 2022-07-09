package orbag.impl.cis;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import orbag.metadata.ConfigurationItem;

@ConfigurationItem
@Entity
public class Server extends RootConfigurationItem {

	@Enumerated(EnumType.STRING)
	ProductiveStage productiveStage;

	@ManyToOne
	Person owner;

	@ManyToOne
	ServerGroup serverGroup;

	String address;

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public ServerGroup getServerGroup() {
		return serverGroup;
	}

	public void setServerGroup(ServerGroup serverGroup) {
		this.serverGroup = serverGroup;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getDisplayLabel() {
		return "name=" + name + ", address=" + address + ", productiveStage=" + productiveStage;
	}

}
