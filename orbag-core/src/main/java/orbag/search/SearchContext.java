package orbag.search;

import org.springframework.security.core.Authentication;

import orbag.data.PaginationInfo;
import orbag.metadata.ConfigurationItemDescriptor;

public class SearchContext {

	ConfigurationItemDescriptor configurationItemDescriptor;

	Authentication user;

	PaginationInfo paginationInfo;
	
	public PaginationInfo getPaginationInfo() {
		
		if (paginationInfo==null ) {
			return new PaginationInfo();
		}
		return paginationInfo;
	}

	public void setPaginationInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}

	public ConfigurationItemDescriptor getConfigurationItemDescriptor() {
		return configurationItemDescriptor;
	}

	public void setConfigurationItemDescriptor(ConfigurationItemDescriptor configurationItemDescriptor) {
		this.configurationItemDescriptor = configurationItemDescriptor;
	}

	public Authentication getUser() {
		return user;
	}

	public void setUser(Authentication user) {
		this.user = user;
	}

}
