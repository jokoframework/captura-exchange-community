package py.com.sodep.mf.exchange.objects.upload;

/**
 * The Status of a given upload. Given a handle (a string) the upload is
 * identified and at a given moment it can be in one of the following states
 * 
 * @author Miguel
 * 
 */
public enum UploadStatus {
	PROGRESS, // The file is being uploaded.
	COMPLETED, // The file transfer ended
	SAVING, // The document has been taken by one of the worker thread and will
			// be send to Mongo
	SAVED, // The file has been successfully stored to mongoDB
	REJECTED, // The document contains some invalid data. E.g. A required field
				// may be missing.
	FAIL, // This a WTF (What a Terrible Failure) situation

	INVALID, // A generic invalid Status. E.g. a nonexistent Upload (an invalid
				// handle) This status will not be persisted in the Database.
				// It exists to set a status when an invalid handle is passed
				// to an upload request
}
