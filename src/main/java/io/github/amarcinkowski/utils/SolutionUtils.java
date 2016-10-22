package io.github.amarcinkowski.utils;

import java.io.File;

public class SolutionUtils {

	private static final String SYS_PATH = "%s/%s/%s";

	private final static String TEST_RESOURCES_FILEPATH = "src/test/resources";

	private static final String SRC_DIR = "src/main/java";

	private static final String SLASH = "/";

	private static final String DOT = ".";

	private static final String JAVA_EXT = "java";

	private static final String FILEPATH_FORMAT_STRING = "%s/%s.%s";

	private final static String getFilename(String canonical) {
		return canonical.substring(canonical.lastIndexOf('.') + 1);
	}

	private final static String getTestResourcesFilepath(String subdir, String filename) {
		return String.format(SYS_PATH, SolutionUtils.TEST_RESOURCES_FILEPATH, subdir, filename);
	}

	private final static String getPackage(String canonical) {
		return canonical.substring(0, canonical.lastIndexOf('.'));
	}

	private final static String getSubdir(String packageName) {
		return packageName.substring(packageName.lastIndexOf('.') + 1);
	}

	public static String getTestResourcePath(String canonical) {
		String packageName = getPackage(canonical);
		String filename = getFilename(canonical);
		String subdir = getSubdir(packageName);
		return getTestResourcesFilepath(subdir, filename);
	}

	public static File getJavaFile(String canonical) {
		String dir = canonical.replace(SolutionUtils.DOT, SolutionUtils.SLASH);
		String path = String.format(SolutionUtils.FILEPATH_FORMAT_STRING, SolutionUtils.SRC_DIR, dir,
				SolutionUtils.JAVA_EXT);
		return new File(path);
	}

}
