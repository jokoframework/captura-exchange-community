package py.com.sodep.mf.exchange.objects.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import py.com.sodep.mf.exchange.objects.upload.MFFileDescriptor;

public class MFExchangeInputStream extends InputStream {

	private MFFileDescriptor fileDescriptor;
	private RandomAccessFile multiplexedFile;
	private long payloadOffset;
	private long channelPosition;
	private long logicalPosition = 0;
	private final int _bufferSize;
	private ByteBuffer buff;

	public MFExchangeInputStream(long payloadOffset, MFFileDescriptor fileDescriptor, RandomAccessFile multiplexedFile,
			int bufferSize) {
		this.payloadOffset = payloadOffset;
		// start the position at the first readable byte of the files
		this.channelPosition = this.payloadOffset + fileDescriptor.getOffset();
		this.fileDescriptor = fileDescriptor;
		this.multiplexedFile = multiplexedFile;
		this._bufferSize = bufferSize;
		this.buff = ByteBuffer.allocate(_bufferSize);
		this.buff.flip();

	}

	/**
	 * A wrapper of the full constructor with an internal buffer of 10K
	 * 
	 * @param payloadOffset
	 * @param fileDescriptor
	 * @param multiplexedFilee
	 */
	public MFExchangeInputStream(long payloadOffset, MFFileDescriptor fileDescriptor, RandomAccessFile multiplexedFilee) {
		this(payloadOffset, fileDescriptor, multiplexedFilee, 10 * 1024);
		// the default internal buffer is of 10K
	}

	@Override
	public int read() throws IOException {

		if (logicalPosition < fileDescriptor.getSize()) {
			logicalPosition++;
			if (buff.hasRemaining()) {
				int b = 0XFF & buff.get();
				return b;
			} else {
				FileChannel channel = multiplexedFile.getChannel();
				buff.clear();

				channel.position(channelPosition);
				int readBytes = channel.read(buff);
				if (readBytes > 0) {
					channelPosition += readBytes;
					buff.flip();

					int b = 0XFF & buff.get();
					return b;
				} else {
					throw new IOException("There are no enough bytes to reassemble the file " + fileDescriptor);
				}
			}
		} else {
			return -1;
		}

	}
}
