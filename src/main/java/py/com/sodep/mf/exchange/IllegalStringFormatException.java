package py.com.sodep.mf.exchange;

public class IllegalStringFormatException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalStringFormatException() {
	}

	public IllegalStringFormatException(String s) {
		super(s);
	}

	public IllegalStringFormatException(Throwable cause) {
		super(cause);
	}

	public IllegalStringFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
