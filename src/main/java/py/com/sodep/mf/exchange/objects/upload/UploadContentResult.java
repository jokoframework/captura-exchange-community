package py.com.sodep.mf.exchange.objects.upload;

public class UploadContentResult extends UploadProgress {

	/**
	 * 
	 * @author Miguel
	 * 
	 */
	public enum Result {
		ACCEPTED, // The request was accepted and saved in the file
		RETRY_LATER, // This is a situation that might arise under
						// heavy load. The
		// device should wait a longer time before sending another
		// requests
		INVALID_RANGE, // An invalid request due to range
		INVALID_LENGTH, // Invalid request due to length
		INVALID_NON_EXISTENT, // Request asking for non existing handle
		UPLOAD_NOT_IN_PROGRESS // If a request to upload data is made but the 
		// the upload has already been COMPLETED, SAVED... or is in any other 
		// state that's not in PROGRESS, the result of the attempt to upload is this
	}

	private Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
