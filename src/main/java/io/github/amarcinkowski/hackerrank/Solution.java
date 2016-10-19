package io.github.amarcinkowski.hackerrank;

import java.io.File;

import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;

public class Solution implements Command {
	
	public final static String BASE_PACKAGE = Solution.class.getPackage().getName();

	protected final static Logger logger = Logger.getLogger(Solution.class);

	private final static String FILEPATH = "src/test/resources";
	private final static String IN = ".in";
	private final static String RESULT = ".result";
	private final static String EXPECTED = ".expected";
	private final String name;

	private File inFile, resultFile, expectedFile;

	public Solution(String name) {
		this.name = name;
		String packageName = name.substring(0, name.lastIndexOf('.'));
		String filename = name.substring(name.lastIndexOf('.') + 1);
		String subdir = packageName.substring(packageName.lastIndexOf('.') + 1);
		String base = String.format("%s/%s/%s", FILEPATH, subdir, filename);
		inFile = new File(base + IN);
		resultFile = new File(base + RESULT);
		expectedFile = new File(base + EXPECTED);
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
