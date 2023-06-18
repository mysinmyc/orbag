package orbag.create;

import orbag.input.*;

/**
 * This interface build input parameters from {@link Input} class annotation</li>
 */
public interface AnnotatedInputConfigurationItemCreationWizard extends ConfigurationItemCreationWizard {

    @Override
    default void buildCreateParameters(FieldGroupBuilder parametersBuilder, CreationContext context) {
        InputUtils.addFieldsFromAnnotatedClass(this.getClass(), parametersBuilder);
    }

    @Override
    default void validate(FieldGroupConsumer parameters, CreationContext context, ConfigurationItemCreationFeedback feedback) {
        InputUtils.forEachAnnotatedField(this.getClass(), (InputField f)-> {
            InputFieldBase<?> parameter = parameters.getField(f.name());
            if (parameter.isEmpty()) {
                feedback.addValidationError("Missing " + parameter.getDisplayLabel(), f.name());
            }
        });
    }
}
