package io.github.amarcinkowski.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.solutionframework.SolutionBuilder;

public class FileUtils {

	public final static String IN_DATA_EXTENSION = ".in";
	public final static String RESULT_EXTENSION = ".result";
	public final static String EXPECTED_EXTENSION = ".expected";

	private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

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
			logger.trace(String.format("Missing %s file. Creating empty.", f.getAbsolutePath()));
			try {
				Path path = Paths.get(f.getAbsolutePath());
				Files.createDirectories(path.getParent());
				Files.createFile(path);
			} catch (Exception e) {
				logger.debug(String.format("Cannot create: ", f.getAbsolutePath()));
			}
		} else if (f.length() == 0) {
			logger.warn(String.format("Empty %s. Aborting.", f.getAbsolutePath()));
		}
	}

	public static String truncateFile(String filepath, String pattern) throws IOException {
		try (Stream<String> lines = SolutionBuilder.fileToStream(filepath)) {
			List<String> replaced = lines.collect(Collectors.toList());
			int i = replaced.size();
			do {
				logger.trace(replaced.get(--i));
			} while (replaced.get(i).matches(pattern));
			return replaced.stream().limit(i).collect(Collectors.joining(System.getProperty("line.separator")));
		}
	}

}
