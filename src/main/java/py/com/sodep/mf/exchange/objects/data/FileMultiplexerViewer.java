package py.com.sodep.mf.exchange.objects.data;

import java.io.File;
import java.io.IOException;

import py.com.sodep.mf.exchange.objects.upload.MFFileDescriptor;
import py.com.sodep.mf.exchange.objects.upload.MFMultiplexedFile;

public class FileMultiplexerViewer {

	public static void main(String[] args) throws IOException {
		String fileName = "doc.mf";
		if (args.length > 0 && args[0] != null) {
			fileName = args[0];
		}

		File file = new File(fileName);
		System.out.println("Parsing file " + file.getAbsolutePath());
		MFMultiplexedFileSerializer serializer = new MFMultiplexedFileSerializer();
		MFMultiplexedFile multiplexedFile = serializer.parse(file);
		System.out.println("Parsed multiplexed file ");

		MFFileDescriptor fileDescriptor = multiplexedFile.getHeader().getFileDescriptor("document");
		System.out.println("Document is " + fileDescriptor);
		String doc = multiplexedFile.getFile("document");

	}

}
