package py.com.sodep.mf.exchange.objects.device;

public class MFDeviceInfo {

	private String os;

	private String identifier;

	private String brand;

	private String model;

	private String versionNumber;

	private String manufacturer;

	private String product;

	private String release;

	/**
	 * The value of the primary key on the entity Devices
	 */
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	/**
	 * 
	 * @return
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((os == null) ? 0 : os.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((release == null) ? 0 : release.hashCode());
		result = prime * result + ((versionNumber == null) ? 0 : versionNumber.hashCode());
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
		if (!(obj instanceof MFDeviceInfo)) {
			return false;
		}
		MFDeviceInfo other = (MFDeviceInfo) obj;
		if (brand == null) {
			if (other.brand != null) {
				return false;
			}
		} else if (!brand.equals(other.brand)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (identifier == null) {
			if (other.identifier != null) {
				return false;
			}
		} else if (!identifier.equals(other.identifier)) {
			return false;
		}
		if (manufacturer == null) {
			if (other.manufacturer != null) {
				return false;
			}
		} else if (!manufacturer.equals(other.manufacturer)) {
			return false;
		}
		if (model == null) {
			if (other.model != null) {
				return false;
			}
		} else if (!model.equals(other.model)) {
			return false;
		}
		if (os == null) {
			if (other.os != null) {
				return false;
			}
		} else if (!os.equals(other.os)) {
			return false;
		}
		if (product == null) {
			if (other.product != null) {
				return false;
			}
		} else if (!product.equals(other.product)) {
			return false;
		}
		if (release == null) {
			if (other.release != null) {
				return false;
			}
		} else if (!release.equals(other.release)) {
			return false;
		}
		if (versionNumber == null) {
			if (other.versionNumber != null) {
				return false;
			}
		} else if (!versionNumber.equals(other.versionNumber)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MFDeviceInfo{" +
				"os='" + os + '\'' +
				", identifier='" + identifier + '\'' +
				", brand='" + brand + '\'' +
				", model='" + model + '\'' +
				", versionNumber='" + versionNumber + '\'' +
				", manufacturer='" + manufacturer + '\'' +
				", product='" + product + '\'' +
				", release='" + release + '\'' +
				", id=" + id +
				'}';
	}
}
