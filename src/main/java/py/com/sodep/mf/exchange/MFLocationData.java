package py.com.sodep.mf.exchange;

public class MFLocationData {

	private Double latitude;
	private Double longitude;
	private Double altitude = 0d;
	private Double accuracy = 0d;

	public MFLocationData(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public MFLocationData(Double latitude, Double longitude, Double altitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}

	public MFLocationData(Double latitude, Double longitude, Double altitude, Double accuracy) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.accuracy = accuracy;
	}

	public MFLocationData() {

	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Double accuracy) {
		this.accuracy = accuracy;
	}

}
