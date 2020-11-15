package py.com.sodep.mf.exchange;

import java.io.Serializable;

/**
 * This represent a column on a given table, it has a name an a type.
 * 
 * @author danicricco
 * 
 */
public class MFField implements Comparable<MFField>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2600515676438540952L;

	public static enum FIELD_TYPE {
		NUMBER, STRING, DATE, BOOLEAN, BLOB, LOCATION
	};

	private FIELD_TYPE type;

	// This field defines the name of the column on a DataSetDefinition. If you
	// don't like the name and plan to refactor please keep in mind that there
	// is at least one weak reference to this field on the DataAccessHelper
	private String columnName;

	private boolean pk;
	private boolean unique;

	public MFField(FIELD_TYPE type, String columnName) {
		this.type = type;
		this.columnName = columnName;
	}

	public MFField() {

	}

	public FIELD_TYPE getType() {
		return type;
	}

	public void setType(FIELD_TYPE type) {
		this.type = type;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Override
	public int compareTo(MFField o) {
		return getColumnName().compareTo(o.getColumnName());
	}

	public boolean isPk() {
		return pk;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + (pk ? 1231 : 1237);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + (unique ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MFField)) {
			return false;
		}
		MFField other = (MFField) obj;
		if (columnName == null) {
			if (other.columnName != null) {
				return false;
			}
		} else if (!columnName.equals(other.columnName)) {
			return false;
		}
		if (pk != other.pk) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		if (unique != other.unique) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MFField [type=" + type + ", columnName=" + columnName + ", pk=" + pk + ", unique=" + unique + "]";
	}

}
