package py.com.sodep.mf.exchange.objects.lookup;

public class LookupTableDefinitionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Indicates that there is another lookup table with the same identifier
	 */
	public static final int IDENTIFIER_ALREADY_EXISTS = 10;

	/**
	 * Indicate that the table definition has more than the maximum number of
	 * fields or columns
	 */
	public static final int MAX_FIELDS = 11;

	private final int errorCode;

	public LookupTableDefinitionException(int errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
