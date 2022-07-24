package orbag.create;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationItemWizardRegistry {

	@Autowired(required = false)
	List<ConfigurationItemWizard> wizards;
	
	public List<ConfigurationItemWizard> getWizards() {
		return wizards;
	}
}
