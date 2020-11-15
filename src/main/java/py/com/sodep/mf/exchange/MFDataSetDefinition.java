package py.com.sodep.mf.exchange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the definition of a lookup table or form data, it is analogous to a
 * "create table" in a DBMS. Note that we are using the same data structure to
 * keep form and lookuptable data.
 * 
 * @author danicricco
 * 
 */
public class MFDataSetDefinition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected List<MFField> fields;

	protected Long version;
	protected String metaDataRef;

	public MFDataSetDefinition(List<MFField> fields, Long version, String metaDataRef) {
		this.fields = fields;
		this.version = version;
		this.metaDataRef = metaDataRef;
	}

	public MFDataSetDefinition(MFDataSetDefinition def) {
		this(def.getFields(), def.getVersion(), def.getMetaDataRef());
	}

	public MFDataSetDefinition() {
		this.fields = new ArrayList<MFField>();
	}

	public MFDataSetDefinition(List<MFField> fields) {
		this.fields = fields;
	}

	public List<MFField> getFields() {
		return fields;
	}

	public Map<String, MFField> fieldsMappedByName() {
		HashMap<String, MFField> map = new HashMap<String, MFField>();
		List<MFField> fields = getFields();
		if (fields != null) {
			for (MFField field : fields) {
				map.put(field.getColumnName(), field);
			}
		}
		return map;
	}

	public void setFields(List<MFField> fields) {
		this.fields = fields;
	}

	public void addField(MFField field) {
		fields.add(field);
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getMetaDataRef() {
		return metaDataRef;
	}

	public void setMetaDataRef(String metaDataRef) {
		this.metaDataRef = metaDataRef;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj))
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MFDataSetDefinition other = (MFDataSetDefinition) obj;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("MFDataSetDefinition [ version=" + version + ", metaDataRef=" + metaDataRef + " fields = ");
		if (fields != null) {
			for (MFField field : this.fields) {
				buff.append(field.toString() + "\n");
			}
		}
		buff.append("]\n");
		return buff.toString();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		return result;
	}
}
