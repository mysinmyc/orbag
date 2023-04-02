package orbag.create;

import orbag.input.FieldGroupBuilder;
import orbag.input.FieldGroupConsumer;
import orbag.input.InputUtils;

public interface ConfigurationItemWizard {

	default void buildCreateParameters(FieldGroupBuilder parametersBuilder, CreationContext context) {
		InputUtils.addFieldsFromAnnotatedClass(this.getClass(), parametersBuilder);
	}

	Object create(FieldGroupConsumer parameters,CreationContext context);
}
