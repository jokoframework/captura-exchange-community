package py.com.sodep.mf.exchange.objects.lookup;

public class LookupTableDTO {

	/**
	 * A unique identifier that may be used by external application to identify
	 * a lookup table
	 */
	private String identifier;
	/**
	 * If this is set to true it means that REST DML (Data Modification
	 * Language) sentences can be executed over this lookup
	 */
	private boolean acceptRESTDMLs;
	/**
	 * An internal identifier assigned by MobileForm
	 */
	private Long pk;

	private Long applicationId;

	/**
	 * A human readable name
	 */
	private String name;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public boolean isAcceptRESTDMLs() {
		return acceptRESTDMLs;
	}

	public void setAcceptRESTDMLs(boolean acceptRESTDMLs) {
		this.acceptRESTDMLs = acceptRESTDMLs;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (acceptRESTDMLs ? 1231 : 1237);
		result = prime * result + ((applicationId == null) ? 0 : applicationId.hashCode());
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		if (!(obj instanceof LookupTableDTO)) {
			return false;
		}
		LookupTableDTO other = (LookupTableDTO) obj;
		if (acceptRESTDMLs != other.acceptRESTDMLs) {
			return false;
		}
		if (applicationId == null) {
			if (other.applicationId != null) {
				return false;
			}
		} else if (!applicationId.equals(other.applicationId)) {
			return false;
		}
		if (identifier == null) {
			if (other.identifier != null) {
				return false;
			}
		} else if (!identifier.equals(other.identifier)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (pk == null) {
			if (other.pk != null) {
				return false;
			}
		} else if (!pk.equals(other.pk)) {
			return false;
		}
		return true;
	}

}
