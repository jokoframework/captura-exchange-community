package py.com.sodep.mf.exchange;

import java.util.Map;

import py.com.sodep.mf.exchange.objects.data.RowCheckError;

/**
 * This is a class that can be used from the GUI to store data and track the
 * errors on a given row. If there are errors the method
 * {@link RowCheckError#getHandle()} will return the same object as
 * {@link #getHandle()}.
 * 
 * @author danicricco
 * 
 */
public interface MFIncomingDataI {
	
	
	public static final String META_FIELD_RECEIVED_AT = "receivedAt";

	public static final String META_FIELD_MAIL = "mail";
	
	public static final String META_FIELD_USER_ID = "user";
	
	public static final String META_FIELD_FORM_ID = "form";
	
	public static final String META_FIELD_LOCATION = "location";
	
	public static final String META_FIELD_DOCUMENT_ID = "document_id";
	
	public static final String META_FIELD_DEVICE_ID = "device_id";
	
	public static final String META_FIELD_SAVED_AT = "savedAt";

	/**
	 * An object that can unequivocally indentify a row. This actually doesn't
	 * have any meaning for the data service, it is meant only to match the
	 * reported errors to the adequate GUI error
	 * 
	 * @return
	 */
	public Object getHandle();

	public Map<String, ?> getData();

	public Map<String, ?> getMeta();

}

