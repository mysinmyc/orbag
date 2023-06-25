package orbag.samples.cis;

import jakarta.persistence.*;

import orbag.samples.impacts.Infrastructure2BusinessAware;
import orbag.samples.security.InfrastructureCisPolicy;
import orbag.metadata.ConfigurationItem;
import orbag.metadata.ConfigurationItemProperty;
import orbag.search.Searcheable;
import orbag.security.AccessRestricted;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@ConfigurationItem(category = "Infrastructure")
@Entity
@AccessRestricted(inherts = InfrastructureCisPolicy.class )
public class Server extends RootConfigurationItem implements Infrastructure2BusinessAware {

	@Enumerated(EnumType.STRING)
	ProductiveStage productiveStage;

	public ProductiveStage getProductiveStage() {
		return productiveStage;
	}

	@ConfigurationItemProperty(category = "Management info")
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

	@ConfigurationItemProperty( displayLabel = "Server owner",category = "Management info")
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

	@ConfigurationItemProperty(mandatoryForCreation = true, category = "Connection info")
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


	@Lazy
	@OneToMany(mappedBy = "server")
	List<ApplicationInstance> installedApplications;

	public List<ApplicationInstance> getInstalledApplications() {
		return installedApplications;
	}

	public void setInstalledApplications(List<ApplicationInstance> installedApplications) {
		this.installedApplications = installedApplications;
	}

	@Override
	public List<?> getBusinessImpacts() {
		return getInstalledApplications();
	}
}
