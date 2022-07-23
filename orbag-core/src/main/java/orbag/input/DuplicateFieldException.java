package orbag.input;

@SuppressWarnings("serial")
public class DuplicateFieldException extends RuntimeException {

	public DuplicateFieldException() {
		super();
	}
	
	public DuplicateFieldException(String message) {
		super(message);
	}
}
