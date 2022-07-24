package orbag.create;

import orbag.input.FieldGroupBuilder;
import orbag.input.FieldGroupConsumer;

public interface ConfigurationItemWizard {

	
	void buildCreateParameters(FieldGroupBuilder parametersBuilder, CreationContext context);

	Object create(FieldGroupConsumer parameters,CreationContext context);
}
