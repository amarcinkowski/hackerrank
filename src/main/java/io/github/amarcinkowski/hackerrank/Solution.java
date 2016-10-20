package io.github.amarcinkowski.hackerrank;

import java.io.File;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.utils.ClassnameUtils;

public class Solution implements Command {

	public final static String BASE_PACKAGE = Solution.class.getPackage().getName();

	public final static Logger logger = LoggerFactory.getLogger(Solution.class);

	private final static String IN_DATA_EXTENSION = "in";
	private final static String RESULT_EXTENSION = "result";
	private final static String EXPECTED_EXTENSION = "expected";
	private final String name;

	private File inFile, resultFile, expectedFile;

	public Solution(String name) {
		this.name = name;
		String packageName = ClassnameUtils.getPackage(name);
		String filename = ClassnameUtils.getFilename(name);
		String subdir = ClassnameUtils.getSubdir(packageName);
		inFile = new File(ClassnameUtils.getFilepath(subdir, filename, IN_DATA_EXTENSION));
		resultFile = new File(ClassnameUtils.getFilepath(subdir, filename, RESULT_EXTENSION));
		expectedFile = new File(ClassnameUtils.getFilepath(subdir, filename, EXPECTED_EXTENSION));
	}

	public Solution() {
		throw new NotImplementedException("used for HR compatibility");
	}

	protected void log(Object log) {
		if (System.getenv("LOGNAME").equals("amarcinkowski")) {
			logger.debug(log.toString());
		}
	}

	public File getInFile() {
		return inFile;
	}

	public File getResultFile() {
		return resultFile;
	}

	public File getExpectedFile() {
		return expectedFile;
	}

	public void execute() {
		throw new NotImplementedException();
	}

	@Override
	public String toString() {
		return name;
	}

}
