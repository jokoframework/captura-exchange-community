package py.com.sodep.mf.exchange;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MFManagedDataBasic implements MFManagedData {

	protected Long rowId;
	protected Long version;
	protected Long ddlVersion;
	protected Map<String, Object> userData;
	protected Map<String, Object> metaData;

	public MFManagedDataBasic(MFManagedData d) {
		this.rowId = d.getRowId();
		this.version = d.getVersion();
		this.ddlVersion = d.getDDLVersion();
		this.userData = d.getUserData();
		this.metaData = d.getMetaData();
	}

	public MFManagedDataBasic() {

	}

	@Override
	public Long getRowId() {
		return rowId;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	@JsonIgnore
	public Long getDDLVersion() {
		return ddlVersion;
	}

	@Override
	/**
	 * The map that contains the data of the user. The fields depends on the
	 * {@link MFDataSetDefinition}
	 */
	public Map<String, Object> getUserData() {
		return userData;
	}

	@Override
	public Object getValue(String field) {
		return userData.get(field);
	}

	@Override
	public String getString(String field) {
		return getValue(field).toString();
	}

	@Override
	public Long getLong(String field) {
		return (Long) userData.get(field);

	}

	@Override
	public Boolean getBoolean(String field) {
		return (Boolean) userData.get(field);

	}

	@Override
	public Double getDouble(String field) {
		return (Double) userData.get(field);
	}

	@Override
	public Date getDate(String field) {
		return (Date) userData.get(field);
	}

	public void setUserData(Map<String, Object> userData) {
		this.userData = userData;
	}

	@JsonIgnore
	public Long getDdlVersion() {
		return ddlVersion;
	}

	public void setDdlVersion(Long ddlVersion) {
		this.ddlVersion = ddlVersion;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public Map<String, Object> getMetaData() {
		return metaData;
	}

	public void setMetaData(Map<String, Object> metaData) {
		this.metaData = metaData;
	}
}
