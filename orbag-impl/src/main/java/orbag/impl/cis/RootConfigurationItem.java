package orbag.impl.cis;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import orbag.metadata.ConfigurationItemProperty;
import orbag.metadata.Manageable;
import orbag.search.Searcheable;

@MappedSuperclass
public class RootConfigurationItem implements Manageable<Long>{

	@GeneratedValue
	@Id
	Long id;
	
	@Searcheable
	String name;	
	
	@Override
	public Long getIdentifier() {
		return this.id;
	}
	
	@Override
	public String getDisplayLabel() {
		return this.name; 
	}
	
	public String getName() {
		return name;
	}

	@ConfigurationItemProperty(mandatoryForCreation = true,readOnly = true)
	public void setName(String name) {
		this.name = name;
	}
	
	

}
