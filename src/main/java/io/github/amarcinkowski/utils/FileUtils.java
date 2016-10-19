package io.github.amarcinkowski.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static int diffsResultExpected(File f1, File f2) throws IOException {
		if (!FileUtils.filesSame(f1, f2)) {
			logger.warn("Diffs: " + FileUtils.getDifferences(f1, f2));
		}
		return FileUtils.getNumberOfDifferences(f1, f2);
	}

	public static int getNumberOfDifferences(File f1, File f2) throws IOException {
		return StringUtils.getLevenshteinDistance(FileUtils.fileToString(f1), FileUtils.fileToString(f2));
	}

	public static String getDifferences(File f1, File f2) throws IOException {
		return StringUtils.difference(FileUtils.fileToString(f1), FileUtils.fileToString(f2));
	}

	public static boolean filesSame(File f1, File f2) throws IOException {
		return Arrays.equals(FileUtils.fileToByteArray(f1), FileUtils.fileToByteArray(f2));
	}

	public static String fileToString(File file) throws IOException {
		return new String(FileUtils.fileToByteArray(file));
	}

	public static byte[] fileToByteArray(File file) throws IOException {
		return Files.readAllBytes(file.toPath());
	}

	public static void createFileIfNotExisting(File f) throws IOException {
		if (!f.exists()) {
			logger.error(String.format("Missing %s file. Creating empty.", f.getAbsolutePath()));
			f.createNewFile();
		} else if (f.length() == 0) {
			logger.error(String.format("Empty %s. Aborting.", f.getAbsolutePath()));
			System.exit(0);
		}
	}

}
