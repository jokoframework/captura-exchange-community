package py.com.sodep.mf.exchange.objects.data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Collection;
import java.util.Set;

import py.com.sodep.mf.exchange.objects.data.MFExchangeHeader.MFFile;
import py.com.sodep.mf.exchange.objects.upload.MFFileDescriptor;
import py.com.sodep.mf.exchange.objects.upload.MFMultiplexedFile;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * This is a thread safe class that provides two handy methods to write a
 * multiplexed file to an outputstream and to read it from a file. see
 * {@link #write(MFMultiplexedFile, OutputStream)} and {@link #parse(File)}
 * </p>
 * <p>
 * Note that the read method receives a file, because it needs the ability to
 * arbitrary change the position of the cursor in order to reassemble each of
 * the multiplexed file.
 * </p>
 * 
 * 
 * @author danicricco
 * 
 */
public class MFMultiplexedFileSerializer {

	private final int _bufferSize;

	/**
	 * 
	 * @param bufferSize
	 *            The size of the internal buffer used for reading data from the
	 *            multiplexed file
	 */
	public MFMultiplexedFileSerializer(int bufferSize) {
		this._bufferSize = bufferSize;
	}

	public MFMultiplexedFileSerializer() {
		this(10 * 1024);
	}

	/**
	 * This is a method that is public only for testing purposes. The method
	 * will return the string representation of the header obtained from the
	 * input channel
	 * 
	 * @param channel
	 * @return
	 * @throws IOException
	 */
	public ByteArrayOutputStream searchHeader(ReadableByteChannel channel) throws IOException {
		ByteBuffer buff = ByteBuffer.allocate(_bufferSize);

		ByteArrayOutputStream headerBytes = new ByteArrayOutputStream();

		boolean foundHeader = false;

		int readBytes = channel.read(buff);
		while (readBytes != -1 && headerBytes.size() < MFExchangeHeader.MAX_EXPECTED_HEADER_SIZE && !foundHeader) {
			buff.flip();
			while (buff.hasRemaining() && !foundHeader) {

				byte c = buff.get();
				if (c == MFExchangeHeader.HEADER_DELIMITER) {
					foundHeader = true;
				}
				headerBytes.write(c);
			}
			buff.clear();
			readBytes = channel.read(buff);
		}
		if (foundHeader) {
			return headerBytes;
		} else {
			throw new RuntimeException("Didn't found the header after analyzing " + headerBytes.size()
					+ " bytes. Max header size is " + MFExchangeHeader.MAX_EXPECTED_HEADER_SIZE + " bytes");
		}

	}

	public String serializeHeader(MFExchangeHeader header) {
		StringBuffer buffer = new StringBuffer();
		Set<String> keys = header.getKeys();
		// serialize all key/value pairs
		for (String k : keys) {
			String value = header.getValue(k);
			buffer.append(k + MFExchangeHeader.KEYVALUE_SEPARATOR + value + MFExchangeHeader.KEYVALUEPAIR_SEPARATOR);
		}
		Collection<MFFile> files = header.getFiles();
		ObjectMapper mapper = new ObjectMapper();
		for (MFFile file : files) {
			String fileDesStr;
			try {
				fileDesStr = mapper.writeValueAsString(file.fileDescriptor);
				buffer.append(MFExchangeHeader.KEY_FILE + MFExchangeHeader.KEYVALUE_SEPARATOR + fileDesStr
						+ MFExchangeHeader.KEYVALUEPAIR_SEPARATOR);
			} catch (JsonGenerationException e) {
				throw new RuntimeException(e);
			} catch (JsonMappingException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				// this is an unexpected exception since we are using a memory
				// source here
				throw new RuntimeException(e);
			}

		}
		buffer.append(MFExchangeHeader.HEADER_DELIMITER);
		return buffer.toString();
	}

	public void write(MFMultiplexedFile mfPackage, OutputStream destinationStream) throws IOException {
		MFExchangeHeader header = mfPackage.getHeader();
		String serializedHeader = serializeHeader(header);
		WritableByteChannel outChannel = Channels.newChannel(destinationStream);
		try {
			destinationStream.write(serializedHeader.getBytes("UTF-8"));

			// Write the header
			Collection<MFFile> files = header.getFiles();
			ByteBuffer exchangeBuff = ByteBuffer.allocate(_bufferSize);
			for (MFFile mfFile : files) {
				ReadableByteChannel inChannel = Channels.newChannel(mfFile.source);
				int bytesRead = inChannel.read(exchangeBuff);
				while (bytesRead != -1) {
					exchangeBuff.flip();
					// send all the available bytes before refilling the buffer
					outChannel.write(exchangeBuff);
					while (exchangeBuff.hasRemaining()) {
						outChannel.write(exchangeBuff);
					}
					exchangeBuff.clear();
					bytesRead = inChannel.read(exchangeBuff);
				}
				inChannel.close();
				mfFile.source.close();
			}

		} catch (UnsupportedEncodingException e) {
			// This shouldn't actually happen
			throw new RuntimeException(e);
		} finally {
			outChannel.close();
		}

	}

	public MFExchangeHeader parseHeader(ReadableByteChannel channel) throws IOException {

		ByteArrayOutputStream baosHeader = searchHeader(channel);
		int payloadOffset = baosHeader.size();
		String headerStr = new String(baosHeader.toByteArray(), "UTF-8");
		if (!headerStr.endsWith(MFExchangeHeader.HEADER_DELIMITER + "")) {
			throw new IllegalArgumentException("The header didn't end with '" + MFExchangeHeader.HEADER_DELIMITER + "'");
		}
		headerStr = headerStr.substring(0, headerStr.length() - 1);
		String[] keyValuePairArrayStr = headerStr.split(MFExchangeHeader.KEYVALUEPAIR_SEPARATOR);
		ObjectMapper mapper = new ObjectMapper();

		MFExchangeHeader header = new MFExchangeHeader();
		header.setPayloadOffset(payloadOffset);
		for (String pair : keyValuePairArrayStr) {

			int index = pair.indexOf(MFExchangeHeader.KEYVALUE_SEPARATOR);
			if (index < 0 || index >= pair.length() - 1) {
				throw new IllegalArgumentException("The header contains an invalid entry. '" + pair
						+ "' . It must be of the form key:value");
			}

			String key = pair.substring(0, index);
			String value = pair.substring(index + 1, pair.length());
			if (key.equals(MFExchangeHeader.KEY_VERSION)) {
				header.setVersion(value);
			} else if (key.equals(MFExchangeHeader.KEY_FILE)) {
				MFFileDescriptor fileDescriptor = mapper.readValue(value, MFFileDescriptor.class);
				header.addFile(fileDescriptor);
			}
		}
		return header;
	}

	/**
	 * This method will search for a valid header in a multiplexed file, parse
	 * it and then return an instance of #MFMultiplexedFile
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public MFMultiplexedFile parse(File f) throws IOException {
		RandomAccessFile multiplexedFile = new RandomAccessFile(f, "r");
		FileChannel fileChannel = multiplexedFile.getChannel();
		MFExchangeHeader header = parseHeader(fileChannel);

		MFMultiplexedFile mfPackage = new MFMultiplexedFile(header, multiplexedFile);

		return mfPackage;

	}
}
