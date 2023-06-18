package orbag.create;

import org.springframework.security.core.Authentication;

import orbag.metadata.ConfigurationItemDescriptor;

/**
 * Holder for creation request details
 */
public class CreationContext {

	ConfigurationItemDescriptor configurationItemDescriptor;

	Authentication user;

	/**
	 * Descriptor of configuration item to be created
	 * @return Configuration Item Descriptpr
	 */
	public ConfigurationItemDescriptor getConfigurationItemDescriptor() {
		return configurationItemDescriptor;
	}

	public void setConfigurationItemDescriptor(ConfigurationItemDescriptor configurationItemDescriptor) {
		this.configurationItemDescriptor = configurationItemDescriptor;
	}

	/**
	 * User that requested the operation
	 * @return
	 */
	public Authentication getUser() {
		return user;
	}

	public void setUser(Authentication user) {
		this.user = user;
	}

}
