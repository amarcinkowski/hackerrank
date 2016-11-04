package io.github.amarcinkowski.solutionframework.browser;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeExecutor {

	private static Logger logger = LoggerFactory.getLogger(ChromeExecutor.class);

	private final static String GOOGLECOMMAND = "google-chrome ";

	public static void open(String s) throws IOException {
		Runtime r = Runtime.getRuntime();
		r.exec(s);
	}

	public static void openBrowser(String domain, String group) {
		try {
			String command = GOOGLECOMMAND
					+ String.format("https://www.hackerrank.com/domains/%s/%s", domain, group);
			open(command);
			logger.info("Running command: " + command);
			Thread.sleep(800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveAndCloseAll(String domain, String group) {
		logger.info("Switch window to chrome");
		RobotHelper.switchWindowAndSaveAll(domain+group);
	}

}
