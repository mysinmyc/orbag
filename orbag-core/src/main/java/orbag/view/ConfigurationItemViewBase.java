package orbag.view;

import org.springframework.beans.factory.BeanNameAware;

public abstract class ConfigurationItemViewBase implements BeanNameAware, ConfigurationItemView{

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
