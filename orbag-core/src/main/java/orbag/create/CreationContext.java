package orbag.create;

import org.springframework.security.core.Authentication;

import orbag.metadata.ConfigurationItemDescriptor;

public class CreationContext {

	ConfigurationItemDescriptor configurationItemDescriptor;

	Authentication user;

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
