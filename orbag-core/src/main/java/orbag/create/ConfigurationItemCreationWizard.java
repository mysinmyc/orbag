package orbag.create;

import orbag.input.*;

/**
 * Describe a Configuration Item Wizard
 *
 * <p>Configuration item wizards are objects that implement the logic to create new instances of CIs
 *
 * <p>
 * To define a CI the following operations are executed
 * <ol>
 *     <li>Build parameters {@link ConfigurationItemCreationWizard#buildCreateParameters(FieldGroupBuilder,CreationContext)}</li>
 *     <li>(On client side: fill parameters)</li>
 *     <li>Validate parameters {@link ConfigurationItemCreationWizard#validate(FieldGroupConsumer, CreationContext, ConfigurationItemCreationFeedback)}</li>
 *     <li>Create CI  {@link ConfigurationItemCreationWizard#create(FieldGroupConsumer, CreationContext)}</li>
 * </ol>
 *
 * @See {@link DefaultConfigurationItemCreationWizard} for default implementation
 *
 */
public interface ConfigurationItemCreationWizard {

	/**
	 * Build parameters required by the wizard
	 *
	 * @param parametersBuilder the parameters builder
	 * @param context the creation context. It contains information about the ConfigurationItem to create and the user that requested it
	 */
	void buildCreateParameters(FieldGroupBuilder parametersBuilder, CreationContext context);

	/**
	 * Validate input filled by the user
	 *
	 * @param parameters parameters filled by the user
	 * @param context the creation context (configuration item and user that requested creation)
	 * @param feedback the feedback of the operation. In case of validation issues execute {@link ConfigurationItemCreationFeedback#addValidationError(String, String)}
	 */
	void validate(FieldGroupConsumer parameters, CreationContext context, ConfigurationItemCreationFeedback feedback);

	/**
	 * Implement the object creation
	 * @param parameters Input parameters
	 * @param context creation context (configuration item descriptor and user that requested the operation)
	 * @return created CI
	 * @throws ConfigurationItemCreationException Exception thrown in case of issues during creation
	 */
	Object create(FieldGroupConsumer parameters,CreationContext context) throws ConfigurationItemCreationException;
}
