package py.com.sodep.mf.exchange.objects.data;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import py.com.sodep.mf.exchange.objects.upload.MFFileDescriptor;
import py.com.sodep.mf.exchange.objects.upload.MFMultiplexedFile;

/**
 * Defines the header of a {@link MFMultiplexedFile}.The Header contains a set
 * of key value pairs encoded in UTF-16. The header will be interpreted as 7
 * bits ASCCI characters.
 * 
 * <p>
 * Each value pair must be separated with a semicolon (;) character and the
 * value must be separated from the key with a (:) The header must be terminated
 * with a hash (#) For example a valid header might be:
 * </p>
 * <p>
 * Example 1 -
 * </p>
 * <code>VERSION: 1.0;# </code>
 * 
 * <p>
 * file-desc is a special property that can be repeated several times. It must
 * contain an object of type
 * </p>
 * 
 * <p>
 * Concurrency
 * </p>
 * This class is not thread save, it does not hold any locks and internally uses
 * a java.util.HashMap to save the key/value pairs
 * */

public class MFExchangeHeader {
	static class MFFile {
		MFFileDescriptor fileDescriptor;
		InputStream source;

		public MFFile(MFFileDescriptor fileDescriptor, InputStream source) {
			this.fileDescriptor = fileDescriptor;
			this.source = source;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((fileDescriptor == null) ? 0 : fileDescriptor.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (super.equals(obj)) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			MFFile other = (MFFile) obj;
			if (fileDescriptor == null) {
				if (other.fileDescriptor != null) {
					return false;
				}
			} else if (!fileDescriptor.equals(other.fileDescriptor)) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return "MFFile [fileDescriptor=" + fileDescriptor + "]";
		}

	}

	// The current version of the protocol. Increment this if there are
	// important changes
	public static final String PROTOCOL_VERSION = "1.0";
	// This is a very big header, if the headear was not correctly terminated
	// before reaching this limit, then a ParseException will be thrown
	public static final long MAX_EXPECTED_HEADER_SIZE = 1024 * 512;

	public static final String KEYVALUEPAIR_SEPARATOR = ";";
	public static final String KEYVALUE_SEPARATOR = ":";

	public static final char HEADER_DELIMITER = '#';

	public static final String KEY_VERSION = "VERSION";

	/** The size of the payload in bytes */
	public static final String KEY_CONTENT_LENGTH = "content-length";

	public static final String KEY_FILE = "file-desc";
	/**
	 * Extra header values not defined in this version of the protocol or user
	 * defined header
	 * */
	private Map<String, String> keyValuePairs;

	private Map<String, MFFile> files;

	private int payloadOffset;

	public MFExchangeHeader() {
		files = new LinkedHashMap<String, MFExchangeHeader.MFFile>();
		keyValuePairs = new HashMap<String, String>();
		setVersion(PROTOCOL_VERSION);
	}

	public void setVersion(String version) {
		keyValuePairs.put(KEY_VERSION, version);
	}

	public long payloadSize() {
		Collection<MFFile> files = getFiles();
		long totalSize = 0;
		for (MFFile mfFile : files) {
			totalSize += mfFile.fileDescriptor.getSize();
		}
		return totalSize;
	}

	public void addFile(String fileName, InputStream inputStream, String contentType, Long size) {
		if (files.containsKey(fileName)) {
			throw new IllegalArgumentException("There is already a file with the name " + fileName + " on this Package");
		}
		if (fileName.contains(KEYVALUEPAIR_SEPARATOR) || fileName.contains(KEYVALUE_SEPARATOR)) {
			throw new IllegalArgumentException("The filename can't contain: " + "'" + KEYVALUEPAIR_SEPARATOR + "'"
					+ "'" + KEYVALUE_SEPARATOR + "'. Offending file name was = '" + fileName + "'");
		}
		// the current payload size is the offset for this file
		long offset = payloadSize();
		MFFileDescriptor fileDescriptor = new MFFileDescriptor(fileName, contentType, offset, size);
		files.put(fileDescriptor.getFileName(), new MFFile(fileDescriptor, inputStream));
	}

	/***
	 * This is a method used when the header is in ReadMode in order to
	 * interpret the multiplexed file
	 * 
	 * @param f
	 */
	void addFile(MFFileDescriptor f) {
		files.put(f.getFileName(), new MFFile(f, null));
	}

	public Set<String> getKeys() {
		return keyValuePairs.keySet();
	}

	public String getValue(String key) {
		return keyValuePairs.get(key);
	}

	public Collection<MFFile> getFiles() {
		return files.values();
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MFExchangeHeader other = (MFExchangeHeader) obj;
		if (files == null) {
			if (other.files != null) {
				return false;
			}
		} else if (!files.equals(other.files)) {
			return false;
		}
		if (keyValuePairs == null) {
			if (other.keyValuePairs != null) {
				return false;
			}
		} else if (!keyValuePairs.equals(other.keyValuePairs)) {
			return false;
		}
		return true;
	}

	/**
	 * Obtain the file descriptor associated to the file
	 * 
	 * @param fileName
	 * @return
	 */
	public MFFileDescriptor getFileDescriptor(String fileName) {
		MFFile file = files.get(fileName);
		return file.fileDescriptor;

	}

	/**
	 * The number of bytes to skip on the multiplexed file before reading the
	 * first byte of the first multiplexed file
	 * */
	public long getPayloadOffset() {
		return payloadOffset;
	}

	public void setPayloadOffset(int payloadOffset) {
		this.payloadOffset = payloadOffset;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((files == null) ? 0 : files.hashCode());
		return result;
	}
}
