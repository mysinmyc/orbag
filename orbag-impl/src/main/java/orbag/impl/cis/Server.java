package orbag.impl.cis;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import orbag.impl.security.InfrastructureCisPolicy;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;
import orbag.search.Searcheable;
import orbag.security.AccessRestricted;

@ConfigurationItem(category = "Infrastructure")
@Entity
@AccessRestricted(inherts = InfrastructureCisPolicy.class )
public class Server extends RootConfigurationItem {

	@Enumerated(EnumType.STRING)
	ProductiveStage productiveStage;

	public ProductiveStage getProductiveStage() {
		return productiveStage;
	}

	@ConfigurationItemProperty
	public void setProductiveStage(ProductiveStage productiveStage) {
		this.productiveStage = productiveStage;
	}

	Boolean installed;
	
	@ConfigurationItemProperty
	public Boolean getInstalled() {
		return installed;
	}

	public void setInstalled(Boolean installed) {
		this.installed = installed;
	}

	@ManyToOne
	Person owner;

	@ManyToOne
	ServerGroup serverGroup;

	@Searcheable
	String address;

	@ConfigurationItemProperty( displayLabel = "Server owner")
	public Person getOwner() {
		return owner;
	}

	
	public void setOwner(Person owner) {
		this.owner = owner;
	}

	@ConfigurationItemProperty
	public ServerGroup getServerGroup() {
		return serverGroup;
	}

	
	public void setServerGroup(ServerGroup serverGroup) {
		this.serverGroup = serverGroup;
	}

	@ConfigurationItemProperty(mandatoryForCreation = true)
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
