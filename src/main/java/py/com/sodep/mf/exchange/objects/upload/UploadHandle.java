package py.com.sodep.mf.exchange.objects.upload;

/**
 * Gives the necessary information to start o resume an upload
 * 
 * @author Miguel
 * 
 */
public class UploadHandle extends UploadProgress {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean acquired;

	// According to #2414 this should be the complete URL.
	// why?
	// Danicricco: The idea is to upload the file to a different server. this
	// way you can have multiple server handling the upload.
	// Note that the upload might block several TCP connections
	private String location;

	private String handle;

	/**
	 * The ticket (#2414) documentation says that this should be a complete
	 * (absolute) URL. However, I don't fully agree right now and since it will
	 * be much easiear if it's a relative url, for the moment it will be a
	 * relative url
	 * 
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public boolean isAcquired() {
		return acquired;
	}

	public void setAcquired(boolean acquired) {
		this.acquired = acquired;
	}

}
