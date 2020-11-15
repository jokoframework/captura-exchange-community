package py.com.sodep.mf.exchange.objects.device;

public class MFDevice {

	private Long applicationId;

	private MFDeviceInfo deviceInfo;

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public MFDeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(MFDeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationId == null) ? 0 : applicationId.hashCode());
		result = prime * result + ((deviceInfo == null) ? 0 : deviceInfo.hashCode());
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
		if (!(obj instanceof MFDevice)) {
			return false;
		}
		MFDevice other = (MFDevice) obj;
		if (applicationId == null) {
			if (other.applicationId != null) {
				return false;
			}
		} else if (!applicationId.equals(other.applicationId)) {
			return false;
		}
		if (deviceInfo == null) {
			if (other.deviceInfo != null) {
				return false;
			}
		} else if (!deviceInfo.equals(other.deviceInfo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MFDevice [applicationId=" + applicationId + ", deviceInfo=" + deviceInfo + "]";
	}

}
