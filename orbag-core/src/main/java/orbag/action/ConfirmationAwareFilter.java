package orbag.action;

public interface ConfirmationAwareFilter {

	default boolean requireConfirmation(ActionRequest request) {
		return true;
	}
}
