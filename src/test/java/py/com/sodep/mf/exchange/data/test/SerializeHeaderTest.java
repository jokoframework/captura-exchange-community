package py.com.sodep.mf.exchange.data.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import junit.framework.Assert;

import org.junit.Test;

import py.com.sodep.mf.exchange.objects.data.MFExchangeHeader;
import py.com.sodep.mf.exchange.objects.data.MFMultiplexedFileSerializer;
import py.com.sodep.mf.exchange.objects.data.MFStreamUtilities;
import py.com.sodep.mf.exchange.objects.upload.MFMultiplexedFile;

public class SerializeHeaderTest {

	@Test
	/**
	 * Test the serialization of the header using an expected string and a parsed string from the memory data
	 * @throws IOException
	 */
	public void testHeaderSerialization() throws IOException {
		String fileOneName = "nave.jpg";
		String fileTwoName = "stonehenge.jpg";
		InputStream fileOne = SerializeHeaderTest.class.getResourceAsStream("/" + fileOneName);
		Assert.assertNotNull(fileOne);
		long countBytesFileOne = MFStreamUtilities.countBytes(fileOne);
		fileOne.close();

		InputStream fileTwo = SerializeHeaderTest.class.getResourceAsStream("/" + fileTwoName);
		Assert.assertNotNull(fileTwo);
		long countBytesFileTwo = MFStreamUtilities.countBytes(fileTwo);
		fileTwo.close();

		MFMultiplexedFile mfPackage = new MFMultiplexedFile();

		fileOne = SerializeHeaderTest.class.getResourceAsStream("/" + fileOneName);
		fileTwo = SerializeHeaderTest.class.getResourceAsStream("/" + fileTwoName);

		MFMultiplexedFileSerializer serializer = new MFMultiplexedFileSerializer();
		mfPackage.addFile(fileOneName, "image/jpeg", countBytesFileOne, fileOne);
		mfPackage.addFile(fileTwoName, "image/jpeg", countBytesFileTwo, fileTwo);

		String serializedHeader = serializer.serializeHeader(mfPackage.getHeader());
		String expectedHeader = "VERSION:1.0;file-desc:{\"fileName\":\"nave.jpg\",\"contentType\":\"image/jpeg\",\"offset\":0,\"size\":41939};file-desc:{\"fileName\":\"stonehenge.jpg\",\"contentType\":\"image/jpeg\",\"offset\":41939,\"size\":194256};#";
		Assert.assertEquals(serializedHeader, expectedHeader);

		ByteArrayInputStream inputSream = new ByteArrayInputStream(serializedHeader.getBytes("UTF-8"));
		ReadableByteChannel inChannel = Channels.newChannel(inputSream);

		// Test if the header can be converted back to its string representation

		ByteArrayOutputStream baosHeader = serializer.searchHeader(inChannel);
		String parsedHeaderStr = new String(baosHeader.toByteArray(), "UTF-8");
		Assert.assertEquals(serializedHeader, parsedHeaderStr);
		inChannel.close();
		inputSream.close();

		inputSream = new ByteArrayInputStream(serializedHeader.getBytes("UTF-8"));
		inChannel = Channels.newChannel(inputSream);
		// Test if the header can be parsed

		MFExchangeHeader parsedHeader = serializer.parseHeader(inChannel);
		Assert.assertEquals(mfPackage.getHeader(), parsedHeader);

	}

	@Test
	/**
	 * Test that there can't be two files with the same name
	 * @throws IOException
	 */
	public void testHeaderFileDuplication() throws IOException {
		String fileOneName = "nave.jpg";
		InputStream fileOne = SerializeHeaderTest.class.getResourceAsStream("/" + fileOneName);
		long countBytesFileOne = MFStreamUtilities.countBytes(fileOne);
		fileOne.close();
		fileOne = SerializeHeaderTest.class.getResourceAsStream("/" + fileOneName);
		MFMultiplexedFile mfPackage = new MFMultiplexedFile();
		mfPackage.addFile(fileOneName, "image/jpeg", countBytesFileOne, fileOne);
		try {
			mfPackage.addFile(fileOneName, "image/jpeg", countBytesFileOne, fileOne);
			Assert.fail("Adding two files with the same name should have failed");
		} catch (IllegalArgumentException e) {
			// we are not doing anything, since this is expected to happen
		}
	}

	@Test
	/**
	 * Test that a lot of files can be added and the header can be serialized and then re-created back
	 * @throws IOException
	 */
	public void testHeaderSerializationBigHeader() throws IOException {
		int numberOfFiles = 1024;
		String fileName = "nave.jpg";
		InputStream file = SerializeHeaderTest.class.getResourceAsStream("/" + fileName);
		long countBytesFileOne = MFStreamUtilities.countBytes(file);
		file.close();
		MFMultiplexedFile mfPackage = new MFMultiplexedFile();
		for (int i = 0; i < numberOfFiles; i++) {
			file = SerializeHeaderTest.class.getResourceAsStream("/" + fileName);
			mfPackage.addFile(i + "_" + fileName, "image/jpeg", countBytesFileOne, file);
		}

		MFMultiplexedFileSerializer serializer = new MFMultiplexedFileSerializer();

		String serializedHeader = serializer.serializeHeader(mfPackage.getHeader());

		ByteArrayInputStream inputSream = new ByteArrayInputStream(serializedHeader.getBytes("UTF-8"));
		ReadableByteChannel inChannel = Channels.newChannel(inputSream);

		ByteArrayOutputStream baosHeader = serializer.searchHeader(inChannel);
		String parsedHeaderStr = new String(baosHeader.toByteArray(), "UTF-8");
		Assert.assertEquals(serializedHeader, parsedHeaderStr);
		inChannel.close();
		inputSream.close();

		inputSream = new ByteArrayInputStream(serializedHeader.getBytes("UTF-8"));
		inChannel = Channels.newChannel(inputSream);
		// Test if the header can be parsed

		MFExchangeHeader parsedHeader = serializer.parseHeader(inChannel);
		Assert.assertEquals(mfPackage.getHeader(), parsedHeader);

	}
}
