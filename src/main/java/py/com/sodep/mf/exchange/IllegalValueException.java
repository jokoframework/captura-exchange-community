package py.com.sodep.mf.exchange;

public class IllegalValueException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalValueException() {
		super();
	}

	public IllegalValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalValueException(String s) {
		super(s);
	}

	public IllegalValueException(Throwable cause) {
		super(cause);
	}

}
