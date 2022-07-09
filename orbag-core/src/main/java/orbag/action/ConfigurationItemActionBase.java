package orbag.action;

import org.springframework.beans.factory.BeanNameAware;

public abstract class ConfigurationItemActionBase implements BeanNameAware, ConfigurationItemAction{

	String identifier;
	
	@Override
	public String getIdentifier() {
		return this.identifier;
	}
	
	@Override
	public void setBeanName(String name) {
		this.identifier = name;	
	}
	
}
