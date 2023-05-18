package orbag.metadata;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnmanagedObjectException extends Exception{

    public UnmanagedObjectException() {
    }

    public UnmanagedObjectException(String message) {
        super(message);
    }

    public UnmanagedObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnmanagedObjectException(Throwable cause) {
        super(cause);
    }
}
