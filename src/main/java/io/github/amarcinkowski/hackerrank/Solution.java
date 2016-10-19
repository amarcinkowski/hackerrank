package io.github.amarcinkowski.hackerrank;

import java.io.File;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Solution implements Command {

	public final static String BASE_PACKAGE = Solution.class.getPackage().getName();

	public final static Logger logger = LoggerFactory.getLogger(Solution.class);

	private final static String TEST_DATA_FILEPATH = "src/test/resources";
	private final static String IN_DATA_EXTENSION = ".in";
	private final static String RESULT_EXTENSION = ".result";
	private final static String EXPECTED_EXTENSION = ".expected";
	private final String name;

	private File inFile, resultFile, expectedFile;

	public Solution(String name) {
		this.name = name;
		String packageName = name.substring(0, name.lastIndexOf('.'));
		String filename = name.substring(name.lastIndexOf('.') + 1);
		String subdir = packageName.substring(packageName.lastIndexOf('.') + 1);
		String base = String.format("%s/%s/%s", TEST_DATA_FILEPATH, subdir, filename);
		inFile = new File(base + IN_DATA_EXTENSION);
		resultFile = new File(base + RESULT_EXTENSION);
		expectedFile = new File(base + EXPECTED_EXTENSION);
	}

	public Solution() {
		throw new NotImplementedException("used for HR compatibility");
	}

	protected void log(Object log) {
		if (System.getenv("LOGNAME").equals("amarcinkowski")) {
			logger.info(log.toString());
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
