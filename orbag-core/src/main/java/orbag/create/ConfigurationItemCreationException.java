package orbag.create;

/**
 * Exception thrown in case of error during configuration item creation
 */
public class ConfigurationItemCreationException extends Exception{

    public ConfigurationItemCreationException() {
    }

    public ConfigurationItemCreationException(String message) {
        super(message);
    }

    public ConfigurationItemCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationItemCreationException(Throwable cause) {
        super(cause);
    }

    public ConfigurationItemCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
