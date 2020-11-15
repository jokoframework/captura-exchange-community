package py.com.sodep.mf.exchange.data.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import py.com.sodep.mf.exchange.objects.data.MFMultiplexedFileSerializer;
import py.com.sodep.mf.exchange.objects.data.MFStreamUtilities;
import py.com.sodep.mf.exchange.objects.upload.MFMultiplexedFile;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializeMultiplexedFileTest {

	private String tempFileName = "test.mf";

	@Test
	public void testFileSerializationAndBackSmallImage() throws IOException {
		String realFileName = "nave.jpg";
		testFileSerializationAndBack(realFileName, 10);
	}

	@Test
	public void testFileSerializationAndBackMediumImage() throws IOException {
		String realFileName = "image-medium.jpeg";
		testFileSerializationAndBack(realFileName, 10);
	}

	@Test
	public void testFileSerializationAndBackHugeImage() throws IOException {
		String realFileName = "image-huge.jpg";
		testFileSerializationAndBack(realFileName, 5);
	}

	public void testFileSerializationAndBack(String realFileName, int numberOfFilestoAdd) throws IOException {

		// 1) Create a multiplexedFile
		MFMultiplexedFile multiplexedFile = new MFMultiplexedFile();

		// 2) Add Files to the multiplexedFile

		for (int i = 0; i < numberOfFilestoAdd; i++) {
			InputStream inStream = SerializeHeaderTest.class.getResourceAsStream("/" + realFileName);
			long countBytes = MFStreamUtilities.countBytes(inStream);
			inStream = SerializeHeaderTest.class.getResourceAsStream("/" + realFileName);
			multiplexedFile.addFile(i + "_" + realFileName, "image/jpeg", countBytes, inStream);
		}

		// 3)Write the multiplexed file to an outputStream

		// Create a serializer with an internal buffer of 256k
		MFMultiplexedFileSerializer serializer = new MFMultiplexedFileSerializer(256 * 1024);
		FileOutputStream outStream = new FileOutputStream(tempFileName);
		serializer.write(multiplexedFile, outStream);
		outStream.close();

		// Just for testing purpose: Read the content of the originally
		// multiplexed file in memory
		InputStream originalFileStream = SerializeHeaderTest.class.getResourceAsStream("/" + realFileName);
		byte[] expectedBytes = MFStreamUtilities.readFromStream(originalFileStream);

		// 4) Parse the multiplexed file
		MFMultiplexedFile multiplexedFileCloned = serializer.parse(new File(tempFileName));

		// 5) Obtain the original file from the multiplexed file

		for (int i = 0; i < numberOfFilestoAdd; i++) {
			InputStream reassembleStream = multiplexedFileCloned.openFile(i + "_" + realFileName, 10 * 1024);
			Assert.assertNotNull(reassembleStream);
			byte[] actualBytes = MFStreamUtilities.readFromStream(reassembleStream);

			// compares that the original file is equal to the file obtained
			// from
			// the multiplexed file
			Assert.assertArrayEquals(expectedBytes, actualBytes);
		}

		// 6) Do not forget to close the multiplexed file
		multiplexedFileCloned.close();

		Assert.assertEquals(multiplexedFile.getHeader(), multiplexedFileCloned.getHeader());
	}

	@Test
	public void testSerializeString() throws IOException {
		// 1) Build the multiplexed file
		String originalString = "This is just a test to check if the Multiplexed file is serializing correctly the data and it can reassemble the files later";
		MFMultiplexedFile multiplexedFile = new MFMultiplexedFile();
		multiplexedFile.addFile("docJson", "text/plain", originalString);

		// 2) Serialize the multiplexed file to the disk
		MFMultiplexedFileSerializer serializer = new MFMultiplexedFileSerializer(256 * 1024);
		FileOutputStream destinationStream = new FileOutputStream(tempFileName);
		serializer.write(multiplexedFile, destinationStream);
		destinationStream.close();

		// 3) Obtain the multiplexed file from the disk
		MFMultiplexedFile multiplexedFileCloned = serializer.parse(new File(tempFileName));
		String actualFile = multiplexedFileCloned.getFile("docJson");
		Assert.assertEquals(originalString, actualFile);

	}

	@Test
	public void testSpecialCharactersInString() throws IOException {
		//BUG #2821
		File tmpFile = new File("accents.test");
		tmpFile.delete();
		tmpFile.deleteOnExit();

		MFMultiplexedFileSerializer serializer = new MFMultiplexedFileSerializer(256 * 1024);

		MFMultiplexedFile multiplexedFile = new MFMultiplexedFile();
		String cafe = "{\"bebida\" : \"café\"}";
		multiplexedFile.addFile("document", "text/plain", cafe);

		FileOutputStream outputStream = new FileOutputStream(tmpFile);
		serializer.write(multiplexedFile, outputStream);
		outputStream.close();

		MFMultiplexedFile parsed = serializer.parse(tmpFile);
		String document = parsed.getFile("document");
		ObjectMapper mapper = new ObjectMapper();
		Map<?, ?> readValue = mapper.readValue(document, Map.class);

		Assert.assertEquals("café", readValue.get("bebida"));
	}

	@After
	public void tearDown() {
		File f = new File(tempFileName);
		f.delete();
	}
}
