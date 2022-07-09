package orbag.server;


@SuppressWarnings("serial")
public class OrbagServerException extends RuntimeException{

	public OrbagServerException() {
		super();
	}

	public OrbagServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrbagServerException(String message) {
		super(message);
	}

	public OrbagServerException(Throwable cause) {
		super(cause);
	}

}
