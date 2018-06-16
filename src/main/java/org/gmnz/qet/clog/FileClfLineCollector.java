package org.gmnz.qet.clog;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;

public class FileClfLineCollector implements ClfLineCollector {

	private PrintWriter out;

	public FileClfLineCollector(String targetFileName) throws LineCollectorCreationException {
		FileSystem fs = FileSystems.getDefault();
		Path targetPath = fs.getPath(targetFileName);
		if (!targetPath.isAbsolute()) {
			targetPath = targetPath.toAbsolutePath().normalize();
		}
		File targetFile = targetPath.toFile();
		if (targetFile.exists() && targetFile.isDirectory()) {
			throw new LineCollectorCreationException("a regular writable file must be specified");
		}
		try {
			out = new PrintWriter(new FileWriter(targetFile, true));
		} catch (IOException e) {
			throw new LineCollectorCreationException("unable to initialize the target file for writing", e);
		}
		System.out.println("initialized at :" + targetFile);
	}

	@Override
	public void receiveLine(String clfLine) {
		out.println(clfLine);
		out.flush();
	}

	@Override
	public void receiveLines(Collection<String> lines) {
		for (String line : lines) {
			out.println(line);
		}
		out.flush();
	}

	public void shutdown() {
		if (out != null) {
			out.close();
			out = null;
		}
	}

	public static void main(String[] args) throws LineCollectorCreationException {
		// FileClfLineCollector fclc = new FileClfLineCollector(null);
		FileClfLineCollector fclc = new FileClfLineCollector("bohboh.txt");

		fclc.receiveLine("uno");
		fclc.receiveLines(Arrays.asList("riga due", "riga 3 ", "rasdlfjj"));

		fclc.shutdown();
	}
}
