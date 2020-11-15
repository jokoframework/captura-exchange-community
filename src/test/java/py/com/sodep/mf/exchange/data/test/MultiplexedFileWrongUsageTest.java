package py.com.sodep.mf.exchange.data.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import py.com.sodep.mf.exchange.objects.data.MFMultiplexedFileSerializer;
import py.com.sodep.mf.exchange.objects.upload.MFMultiplexedFile;

public class MultiplexedFileWrongUsageTest {

	private String tempFileName = "test.mf";

	@Test
	public void testReadAndWriteMode() throws IOException {
		String originalString = "This is just a test to check if the Multiplexed file is serializing correctly the data and it can reassemble the files later";
		MFMultiplexedFile writeMultiplexedFile = new MFMultiplexedFile();
		writeMultiplexedFile.addFile("docJson", "text/plain", originalString);
		Assert.assertEquals(false, writeMultiplexedFile.isReadMode());

		// 2) Serialize the multiplexed file to the disk
		MFMultiplexedFileSerializer serializer = new MFMultiplexedFileSerializer(256 * 1024);
		FileOutputStream destinationStream = new FileOutputStream(tempFileName);
		serializer.write(writeMultiplexedFile, destinationStream);
		destinationStream.close();

		// 3) Obtain the multiplexed file from the disk
		MFMultiplexedFile readMultiplexedFile = serializer.parse(new File(tempFileName));
		Assert.assertEquals(true, readMultiplexedFile.isReadMode());
		String actualFile = readMultiplexedFile.getFile("docJson");
		Assert.assertEquals(originalString, actualFile);

		try {
			readMultiplexedFile.addFile("docJson2", "text/plain", originalString);
			Assert.fail("It shouldn't be possible to add files to a read-mode multiplexed file");
		} catch (IllegalStateException e) {
			// This is actually expected to happen. It is not possible to add
			// files to a read-mode multiplexed file
		}

		// In the same way, it shouldn't be possible to read a file from a
		// write-mode multiplexed file
		try {
			writeMultiplexedFile.openFile("docJson", 10 * 1024);
			Assert.fail("It shouldn't be possible to read files from a write-mode multiplexed file");
		} catch (IllegalStateException e) {

		}
	}

	@After
	public void tearDown() {
		File f = new File(tempFileName);
		f.delete();
	}
}
