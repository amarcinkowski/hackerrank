package io.github.amarcinkowski.hackerrank.browser;

import java.util.stream.Collectors;

import io.github.amarcinkowski.hackerrank.HackerrankJson;
import io.github.amarcinkowski.utils.StringUtils;

public class HackerrankPageReader {

	private static final String JSON_PATTERN = "HR.PREFETCH_DATA =(.*);[ \n]+HR.MANIFEST_HASH";

	public static void list() {
		ChromeExecutor.openBrowser("java", "java-introduction");
		String source = RobotHelper.getSource();
		RobotHelper.closePage();
		String p = JSON_PATTERN;
		String json = StringUtils.findAllMultiline(p, source).stream().collect(Collectors.joining());
		System.out.println(HackerrankJson.hackerrankJsonToList(json));
	}

}
