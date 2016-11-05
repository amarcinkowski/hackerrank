package io.github.amarcinkowski.solutionframework.browser;

import java.util.stream.Collectors;

import io.github.amarcinkowski.utils.SearchUtils;

public class PageReader {

	private static final String JSON_PATTERN = "HR.PREFETCH_DATA =(.*);[ \n]+HR.MANIFEST_HASH";
	public static final String HACKERRANK_URL = "https://www.hackerrank.com/domains/java/java-introduction";
	public static final String CODILITY_URL = "https://codility.com/programmers/lessons/1-iterations/";

	public static void printList(String source) {
		String json = SearchUtils.findAllMultiline(JSON_PATTERN, source).stream().collect(Collectors.joining());
		System.out.println(HackerrankJson.hackerrankJsonToList(json));
	}

	public static String source(String url) {
		ChromeExecutor.openBrowser(url);
		String source = RobotHelper.getSource();
		RobotHelper.closePage();
		return source;
	}

}
