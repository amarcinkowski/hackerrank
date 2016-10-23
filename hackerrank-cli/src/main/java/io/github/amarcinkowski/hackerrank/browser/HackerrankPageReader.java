package io.github.amarcinkowski.hackerrank.browser;

import java.util.stream.Collectors;

import io.github.amarcinkowski.hackerrank.Cli;
import io.github.amarcinkowski.hackerrank.HackerrankJson;

public class HackerrankPageReader {

	public static void list() {
		ChromeExecutor.openBrowser("java", "java-introduction");
		String source = RobotHelper.getSource();
		RobotHelper.closePage();
		String p = Cli.JSON_PATTERN;
		String json = Cli.findAllMultiline(p, source).stream().collect(Collectors.joining());
		System.out.println(HackerrankJson.hackerrankJsonToList(json));
	}

}
