package py.com.sodep.mf.exchange.objects.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class MFStreamUtilities {

	private static final double byteAllocationFactor = 0.3;
	private static final int bufferSize = 10 * 1024;// 10K

	/**
	 * Continouslly reads the data from the inputStream and return a byte array
	 * of the read data. The parameter maxSize can be used to prevent reading
	 * too many data into the memory. The inputStream won't be closed.s
	 * 
	 * @param in
	 * @param maxSize
	 *            The max number of data to read. A negative value means that
	 *            the maxSize won't be checked
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFromStream(InputStream in, int maxSize) throws IOException {

		//
		int initialByteArraySize = 10 * 1024;

		if (maxSize < 0) {
			maxSize = Integer.MAX_VALUE;
		} else {
			if (maxSize < 10 * 1024) {
				initialByteArraySize = (int) (bufferSize * byteAllocationFactor);
			}
		}
		ReadableByteChannel inChannel = Channels.newChannel(in);

		ByteBuffer buff = ByteBuffer.allocate(bufferSize);
		// allocate a buffer of the x percentage of the expected max size
		ByteArrayOutputStream baos = new ByteArrayOutputStream(initialByteArraySize);
		WritableByteChannel writeChannel = Channels.newChannel(baos);
		int readBytes = inChannel.read(buff);
		int totalBytes = 0;
		while (readBytes > 0 && totalBytes < maxSize) {
			totalBytes += readBytes;
			buff.flip();
			writeChannel.write(buff);
			buff.clear();
			readBytes = inChannel.read(buff);
		}

		writeChannel.close();
		if (totalBytes >= maxSize) {
			// exceed the number of expected bytes
			throw new IllegalStateException("Exceeded the number of expected bytes");
		}
		return baos.toByteArray();
	}

	/**
	 * This is a wrapper of {@link #readFromStream(InputStream, long)} that do
	 * not specify any max size. Use this wisely, since it might allocate lot of
	 * data.
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFromStream(InputStream in) throws IOException {
		return readFromStream(in, -1);
	}

	public static long countBytes(InputStream file) throws IOException {
		ReadableByteChannel inChannel = Channels.newChannel(file);
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int bytesRead = inChannel.read(buf);
		long totalBytesRead = 0;
		while (bytesRead != -1) {
			totalBytesRead += bytesRead;
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		inChannel.close();
		return totalBytesRead;
	}
}
