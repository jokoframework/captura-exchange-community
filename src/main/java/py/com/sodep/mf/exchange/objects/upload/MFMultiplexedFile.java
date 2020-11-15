package py.com.sodep.mf.exchange.objects.upload;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import py.com.sodep.mf.exchange.objects.data.MFExchangeHeader;
import py.com.sodep.mf.exchange.objects.data.MFExchangeInputStream;
import py.com.sodep.mf.exchange.objects.data.MFMultiplexedFileSerializer;
import py.com.sodep.mf.exchange.objects.data.MFStreamUtilities;

/***
 * <p>
 * An MFExchangePackage is an abstraction to multiplex several files into a
 * single file and then demultiplex them separately.
 * </p>
 * The class can be either on read mode or write mode, but not both at the same
 * time (see {@link #isReadMode()})
 * <p>
 * In write mode the class can be used to add several files and then use the
 * {@link MFMultiplexedFileSerializer} to create a unique file with the content
 * of the files and a header that describes how to reassemble each of them
 * separately.
 * </p>
 * <p>
 * In read mode the class can be used to obtain InputStream for every single
 * file multiplexed. Each of these InputStream will point to different portions
 * of the same multiplexed file.
 * </p>
 * <p>
 * This class should be used in conjunction with
 * {@link MFMultiplexedFileSerializer} in order to write and read multiplexed
 * files
 * </p>
 * 
 * @author danicricco
 * 
 */
public class MFMultiplexedFile {

	private MFExchangeHeader header;

	private RandomAccessFile multiplexedFile;

	public MFMultiplexedFile() {
		header = new MFExchangeHeader();
	}

	public MFMultiplexedFile(MFExchangeHeader header, RandomAccessFile multiplexedFile) {
		this.header = header;
		this.multiplexedFile = multiplexedFile;
	}

	public boolean isReadMode() {
		return multiplexedFile != null;
	}

	public InputStream openFile(String fileName, int bufferSize) {
		if (!isReadMode()) {
			throw new IllegalStateException("It is not possible to read from a write-mode multiplexed file");
		}
		MFFileDescriptor fileDesc = header.getFileDescriptor(fileName);
		MFExchangeInputStream stream = new MFExchangeInputStream(header.getPayloadOffset(), fileDesc, multiplexedFile,
				bufferSize);

		return stream;
	}

	public MFExchangeHeader getHeader() {
		return header;
	}

	public Long getFileSize(String fileName) {
		return header.getFileDescriptor(fileName).getSize();

	}

	public String getContentType(String fileName) {
		return header.getFileDescriptor(fileName).getContentType();
	}

	/**
	 * This method interpret the file as an UTF-8 String.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public String getFile(String fileName) throws IOException {
		InputStream stream = openFile(fileName, 10 * 1024);
		byte[] fileContent = MFStreamUtilities.readFromStream(stream);
		return new String(fileContent, "UTF-8");

	}

	/**
	 * This method can only be used in write mode to add several files into a
	 * single file.
	 * 
	 * @param fileName
	 * @param contentType
	 * @param fileSize
	 * @param file
	 * @throws IOException
	 */
	public void addFile(String fileName, String contentType, long fileSize, InputStream file) throws IOException {
		if (isReadMode()) {
			throw new IllegalStateException("MFExchangePackage#addFile can't be used in read mode");
		}

		header.addFile(fileName, file, contentType, fileSize);
	}

	public void addFile(String fileName, String contentType, String fileContent) throws IOException {
		try {
			byte[] byteArray = fileContent.getBytes("UTF-8");
			ByteArrayInputStream stream = new ByteArrayInputStream(byteArray);
			// bug #2821
			//addFile(fileName, contentType, fileContent.length(), stream);
			addFile(fileName, contentType, byteArray.length, stream);
		} catch (UnsupportedEncodingException e) {
			// wraps this on a RuntimeException, since it isn't really something
			// to take care off
			throw new RuntimeException(e);
		}
	}

	/**
	 * @throws IOException
	 * */
	public void close() throws IOException {
		if (multiplexedFile != null) {
			multiplexedFile.getChannel().close();
			multiplexedFile.close();
		}
	}

}
