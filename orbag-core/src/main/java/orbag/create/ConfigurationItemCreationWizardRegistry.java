package orbag.create;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Provide access to all the defined configuration item wizards
 *
 * <p> See {@link ConfigurationItemCreationWizard} for more information
 */
@Component
public class ConfigurationItemCreationWizardRegistry {
	@Autowired(required = false)
	List<ConfigurationItemCreationWizard> wizards;

	/**
	 * Configuration item creation wizards
	 * @return all the Configuration item creation wizards
	 */
	public List<ConfigurationItemCreationWizard> getWizards() {
		return wizards;
	}
}
