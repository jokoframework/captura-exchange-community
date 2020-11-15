package py.com.sodep.mf.exchange;

import java.util.Date;
import java.util.Map;
//TODO Document!
// Why is it Managed?
public interface MFManagedData {

	public Long getRowId();

	public Long getVersion();

	public Long getDDLVersion();

	public Map<String, Object> getUserData();

	public Map<String, Object> getMetaData();

	/**
	 * This is a handy method to access directly the user data. It is a shortcut
	 * for getUserData().get("field")
	 * 
	 * @param field
	 * @return
	 */
	public Object getValue(String field);

	/**
	 * A wrapper for {@link #getVersion()} that automatically cast the value to
	 * String
	 * 
	 * @param field
	 * @return
	 */
	public String getString(String field);

	/**
	 * A wrapper for {@link #getVersion()} that automatically cast the value to
	 * Long
	 * 
	 * @param field
	 * @return
	 */
	public Long getLong(String field);

	/**
	 * A wrapper for {@link #getVersion()} that automatically cast the value to
	 * Boolean
	 * 
	 * @param field
	 * @return
	 */
	public Boolean getBoolean(String field);

	/**
	 * A wrapper for {@link #getVersion()} that automatically cast the value to
	 * Double
	 * 
	 * @param field
	 * @return
	 */
	public Double getDouble(String field);

	/**
	 * A wrapper that automatically cast the value to Date
	 * 
	 * @param field
	 * @return
	 */
	public Date getDate(String field);
}
