package py.com.sodep.mf.exchange.objects.metadata;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * Contains details of the synchronization error.
 * </p>
 * <p>
 * The field {@link #type} classifies the errors.
 * </p>
 * <p>
 * The field i18nMessage contains an i18n key that can be used to later
 * translate the error to the appropriate user language. Since the target
 * platform might not have i18nSupport, this class provides the handy method
 * {@link #getEnglishMessage()} that will translate the i18nMessage to a human
 * readable english message.
 * </p>
 * The list of all available errors can be seen on the internal map
 * {@link #i18nToEnglish}
 * 
 * @author danicricco
 * 
 */
// This class is candidate to be moved to mf_android_captura_exchange or
// to disappear completely
@Deprecated
public class SynchronizationError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Deprecated
	public enum ErrorType {
		// A) ERRORS INTERESTING FOR THE USER
		AUTHENTICATION_ERROR, // AUTHENTICATION_ERROR: the user has provided
								// wrong username or
								// password
		CONNECTION_FAILED, // CONNECTION_FAILED: if the user is waiting for
							// network, then it might
							// be interested to notify him that the network
							// connection has failed. Otherwise, he should just
							// proceed and use the system
		// B)ERRORS THAT MIGHT NOT BE INTERESTED FOR THE USER (i.e. confusing)
		// These errors might be caused by a wrong configuration or utilization
		// of the DatomoSynchronizer. It doesn't make much sense to show them to
		// the user.
		// If one of these errors appear, they can be mapped to something
		// generic like "Network error"
		BAD_CONFIGURATION, // BAD_CONFIGURATION: something is wrong on the
							// configuration
		PARSE_ERROR, // PARSE_ERROR: I think that this can only happen if the
						// server is
						// sending unexpected data. If the connection got broken
						// during the download of data, the error reported will
						// be CONNECTION_FAILED
		CONNECTION_TIMEOUT, // couldn't establish a connection to the server
							// within the specified milliseconds (see
							// ConnectionParameters)
		SERVER_UNREACHABLE, TOO_MANY_DEVICES, // An Application has a license,
												// and that license defines a
												// maximum
		// number of devices per user. If that limit is reached, the device will
		// get SynchronizationError
		// of this type
		RESPONSE_CODE_NOT_200
	};

	private final ErrorType type;

	private int httpResponseCode;

	private Exception exception;

	private Map<String, String> serverMessages;

	public SynchronizationError(ErrorType type, Exception exception) {
		this.type = type;
		this.exception = exception;
	}

	public SynchronizationError(ErrorType type) {
		this.type = type;
	}

	public ErrorType getType() {
		return type;
	}

	public int getHttpResponseCode() {
		return httpResponseCode;
	}

	public void setHttpResponseCode(int httpError) {
		this.httpResponseCode = httpError;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public Map<String, String> getServerMessages() {
		return serverMessages;
	}

	public void setServerMessages(Map<String, String> serverMessages) {
		this.serverMessages = serverMessages;
	}

}
