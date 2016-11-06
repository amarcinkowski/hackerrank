package io.github.amarcinkowski.solutionframework.platform;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.xpath.XPathExpressionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.fastxpathaccess.Xpath;
import io.github.amarcinkowski.solutionframework.SolutionBuilder;
import io.github.amarcinkowski.utils.SearchUtils;
import io.github.amarcinkowski.utils.StringUtils;
import io.github.amarcinkowski.utils.XMLUtils;

// TODO a lot of refactoring here 
public class PageReader {

	private final static Logger logger = LoggerFactory.getLogger(PageReader.class);

	private static final String HACKERRANK_JSON_PATTERN = "HR.PREFETCH_DATA =(.*);[ \n]+HR.MANIFEST_HASH";
	public static final String HACKERRANK_URL = "https://www.hackerrank.com/domains/%s/%s";
	public static final String CODILITY_URL = "https://codility.com/programmers/lessons/%s/";

	private static void printHR() {
		String source = source(String.format(PageReader.HACKERRANK_URL, "java", "java-introduction"));
		String json = SearchUtils.findAllMultiline(HACKERRANK_JSON_PATTERN, source).stream()
				.collect(Collectors.joining());
		System.out.println(HackerrankJson.hackerrankJsonToList(json));
	}

	private static Map<String, String> merge(String[] array1, String[] array2) {
		HashMap<String, String> map = new HashMap<>();
		IntStream.range(0, array1.length).forEach(i -> map.put(array1[i], array2[i]));
		return map;
	}

	private static String reformatName(String name) {
		String s = Arrays.asList(name.replaceAll("[\t ]+", "").split("\n+")).stream().collect(Collectors.joining(","))
				.substring(1);
		return s;
	}

	private static Map<String, String> sourceToMap(String source)
			throws XPathExpressionException, UnsupportedEncodingException, IOException {
		String xml = XMLUtils.toXHTML(source);
		String[] names = Xpath.find(xml, "//a[contains(@class, 'lesson-item')]");
		names = Arrays.asList(names).stream().map(e -> reformatName(e)).toArray(String[]::new);
		String[] hrefs = Xpath.find(xml, "//a[contains(@class, 'lesson-item')]/@href");
		return merge(names, hrefs);
	}

	private static String map2String(Map<String, String> map) {
		return map.entrySet().stream().sorted(Map.Entry.<String, String>comparingByKey())
				.map(entry -> entry.getKey() + " => " + entry.getValue()).collect(Collectors.joining("\n"));
	}

	private static void printC() throws XPathExpressionException, UnsupportedEncodingException, IOException {
		String source = PageReader.source(String.format(PageReader.CODILITY_URL, "1-iterations"));
		Map<String, String> map = sourceToMap(source);
		System.out.println(map2String(map));
	}

	public static void printList(String url) {
		switch (url) {
		case HACKERRANK_URL:
			printHR();
			break;
		case CODILITY_URL:
			try {
				printC();
			} catch (XPathExpressionException | IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public static String source(String url) {
		ChromeExecutor.openBrowser(url);
		String source = RobotHelper.getSource();
		RobotHelper.closePage();
		return source;
	}

	public static String getPageContent(String url) {
		ChromeExecutor.openBrowser(url);
		String pageContent = RobotHelper.getPageContent();
		return pageContent;
	}

	// PageReader.HR_UNSOLVED_CHALLENGE_PATTERN
	public static List<String> unsolved(String pageContent, String pattern) {
		return SearchUtils.findAllMultiline(pattern, pageContent);
	}

	private static final String HR_UNSOLVED_CHALLENGE_PATTERN = "([A-Za-z0-9\\- ]+)\nSuccess Rate: .{2,7} Max Score: [0-9]+ Difficulty: [A-Za-z]+ Solve Challenge\n";
	private static final String C_UNSOLVED_CHALLENGE_PATTERN = "([A-Za-z0-9\\- ]+)\nCOMMENT VIEW  START\n";

	private static final void generateHR(String domain, String subdomain) throws IOException {
		String pageContent = PageReader.getPageContent(String.format(PageReader.HACKERRANK_URL, domain, subdomain));
		for (String challenge : PageReader.unsolved(pageContent, PageReader.HR_UNSOLVED_CHALLENGE_PATTERN)) {
			String classname = StringUtils.camelify(challenge);
			logger.info(String.format("%s => %s", challenge, classname));
			new SolutionBuilder().className(classname).subdomain(subdomain).domain(domain).platform("hackerrank")
					.description(challenge.trim()).build();
		}
	}

	private static final void generateC(String domain, String subdomain) throws IOException {
		String pageContent = PageReader.getPageContent(String.format(PageReader.CODILITY_URL, subdomain));
		for (String challenge : PageReader.unsolved(pageContent, PageReader.C_UNSOLVED_CHALLENGE_PATTERN)) {
			String classname = StringUtils.camelify(challenge);
			logger.info(String.format("%s => %s", challenge, classname));
			new SolutionBuilder().className(classname).subdomain(subdomain).domain(domain).platform("codility")
					.description(challenge.trim()).build();
		}
	}
	
	public static void generate(String url, String platform, String domain, String subdomain) throws IOException {
		switch (url) {
		case HACKERRANK_URL:
			generateHR(domain, subdomain);
			break;
		case CODILITY_URL:
			generateC(domain, subdomain);
			break;
		}
	}

}
