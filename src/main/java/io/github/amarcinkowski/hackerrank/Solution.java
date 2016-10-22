package io.github.amarcinkowski.hackerrank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Solution implements Command {

	public final static String BASE_PACKAGE = Solution.class.getPackage().getName();

	public final static Logger logger = LoggerFactory.getLogger(Solution.class);

	public final static String IN_DATA_EXTENSION = ".in";
	public final static String RESULT_EXTENSION = ".result";
	public final static String EXPECTED_EXTENSION = ".expected";
	private final String name;

	public Solution(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Solution() {
		throw new NotImplementedException();
	}

	protected void log(Object log) {
		if (System.getenv("LOGNAME").equals("amarcinkowski")) {
			logger.debug(log.toString());
		}
	}

	public void execute() {
		throw new NotImplementedException();
	}

	@Override
	public String toString() {
		return name;
	}

}
