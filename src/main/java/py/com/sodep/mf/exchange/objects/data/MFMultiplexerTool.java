package py.com.sodep.mf.exchange.objects.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import py.com.sodep.mf.exchange.objects.upload.MFMultiplexedFile;

public class MFMultiplexerTool {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String dir = "multiplexed/";
		String documentFile = dir + "22_document.json";
		String attachments[] = new String[] { "mf--195870318.jpg", "mf--987366763.jpg", "mf-2010990856.jpg" };

		MFMultiplexedFile multiplexedFile = new MFMultiplexedFile();
		FileInputStream docIn = new FileInputStream(documentFile);
		byte[] byteOfDOC = MFStreamUtilities.readFromStream(docIn);
		docIn.close();
		String saveRequestJSON = new String(byteOfDOC, "UTF-8");
		System.out.println("Building multiplexed file");
		System.out.println(saveRequestJSON);
		multiplexedFile.addFile("document", "application/json", saveRequestJSON);

		ArrayList<InputStream> streams = new ArrayList<InputStream>();
		for (String attach : attachments) {
			File attachFile = new File(dir + attach);
			FileInputStream attachFileStream = new FileInputStream(dir + attach);
			multiplexedFile.addFile(attach, "application/json", attachFile.length(), attachFileStream);
			streams.add(attachFileStream);
		}
		File outputFile = new File("mf-output.mf");
		FileOutputStream outStream = new FileOutputStream(outputFile);
		MFMultiplexedFileSerializer serializer = new MFMultiplexedFileSerializer();
		serializer.write(multiplexedFile, outStream);

		outStream.close();
		// close all inputStreams
		for (InputStream in : streams) {
			in.close();
		}

		MFMultiplexedFile parsedFile = serializer.parse(outputFile);
		String docObtained = parsedFile.getFile("document");
		if (docObtained.equals(saveRequestJSON)) {
			System.out.println("Everything went ok");
		}
	}
}
