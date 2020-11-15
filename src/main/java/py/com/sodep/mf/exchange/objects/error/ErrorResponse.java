package py.com.sodep.mf.exchange.objects.error;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * When there's an uncaught Exception in a call to the Web API. A JSON
 * representation of an instance of this class is returned to the client
 * 
 * @author jmpr
 * 
 */
@JsonInclude(Include.NON_NULL)
public class ErrorResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long logId;

	private Integer errorCode;

	private String message;

	private Map<String, String> i18nMessages;

	private ErrorType errorType;

	public ErrorResponse() {

	}

	public ErrorResponse(String msg) {
		this.message = msg;
	}

	public ErrorResponse(String msg, int errorCode) {
		this.errorCode = errorCode;
	}

	@JsonIgnore
	public String getMessageInDefaultLanguage() {
		String language = Locale.getDefault().getLanguage();
		if (i18nMessages != null && i18nMessages.containsKey(language)) {
			return i18nMessages.get(language);
		}
		return null;
	}

	/**
	 * Id of the error logged in the server
	 * 
	 * @param logId
	 */
	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	/**
	 * A default message. Not internationalized.
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * I18N messages. The key is the language
	 * 
	 * @return
	 */
	public Map<String, String> getI18NMessages() {
		return i18nMessages;
	}

	public void setI18NMessages(Map<String, String> messages) {
		this.i18nMessages = messages;
	}

	/**
	 * We need to be able to identify the kind of error in some cases
	 * 
	 * @return
	 */
	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

}
