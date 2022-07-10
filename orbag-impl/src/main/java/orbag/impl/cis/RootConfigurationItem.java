package orbag.impl.cis;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import orbag.metadata.Manageable;

@MappedSuperclass
public class RootConfigurationItem implements Manageable<Long>{

	@Id
	Long id;
	
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

	public void setName(String name) {
		this.name = name;
	}
	
	

}
