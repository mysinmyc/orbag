package orbag.action;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@SuppressWarnings("serial")
public class ActionExecutionException extends Exception {
    public ActionExecutionException() {
    }

    public ActionExecutionException(String message) {
        super(message);
    }

    public ActionExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionExecutionException(Throwable cause) {
        super(cause);
    }

    public ActionExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
