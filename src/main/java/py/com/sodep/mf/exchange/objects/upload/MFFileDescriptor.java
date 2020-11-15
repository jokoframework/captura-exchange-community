package py.com.sodep.mf.exchange.objects.upload;

/**
 * This is the meta information that is useful to read a given file within an
 * {@link MFMultiplexedFile}.
 * */
public class MFFileDescriptor {

	private String fileName;
	private String contentType;
	/** The number of bytes to skip before reaching the first byte of this file */
	private long offset;
	/** The number of bytes of the file */
	private long size;

	public MFFileDescriptor() {

	}

	public MFFileDescriptor(String fileName, String contentType, long offset, long size) {
		this.fileName = fileName;
		this.contentType = contentType;
		this.offset = offset;
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + (int) (offset ^ (offset >>> 32));
		result = prime * result + (int) (size ^ (size >>> 32));
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		MFFileDescriptor other = (MFFileDescriptor) obj;
		if (contentType == null) {
			if (other.contentType != null) {
				return false;
			}
		} else if (!contentType.equals(other.contentType)) {
			return false;
		}
		if (fileName == null) {
			if (other.fileName != null) {
				return false;
			}
		} else if (!fileName.equals(other.fileName)) {
			return false;
		}
		if (offset != other.offset) {
			return false;
		}
		if (size != other.size) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MFFileDescriptor [fileName=" + fileName + ", contentType=" + contentType + ", offset=" + offset
				+ ", size=" + size + "]";
	}

}
