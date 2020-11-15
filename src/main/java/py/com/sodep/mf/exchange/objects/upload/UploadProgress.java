package py.com.sodep.mf.exchange.objects.upload;

import java.io.Serializable;


/**
 * Gives information about the progress of a given upload.
 * 
 * The status of the upload and the number of bytes received.
 * 
 * @author Miguel
 * 
 */
public class UploadProgress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UploadStatus status;

	private long receivedBytes;
	
	private long size;

	public UploadProgress() {

	}

	public UploadProgress(UploadStatus status, int receivedBytes) {
		super();
		this.status = status;
		this.receivedBytes = receivedBytes;
	}

	public UploadStatus getStatus() {
		return status;
	}

	public void setStatus(UploadStatus status) {
		this.status = status;
	}

	public long getReceivedBytes() {
		return receivedBytes;
	}

	public void setReceivedBytes(long receivedBytes) {
		this.receivedBytes = receivedBytes;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
	
}
