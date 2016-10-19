package io.gihtub.amarcinkowski.hackerrank.java;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

import io.gihtub.amarcinkowski.hackerrank.java.tests.Progress;
import io.github.amarcinkowski.hackerrank.Solution;
import io.github.amarcinkowski.hackerrank.SolutionBuilder;
import io.github.amarcinkowski.hackerrank.SolutionFactory;
import io.github.amarcinkowski.hackerrank.TestInfo;
import io.github.amarcinkowski.utils.FileUtils;
import io.github.amarcinkowski.utils.IOUtils;

public class HackerRankTest {

	public static Logger log = Logger.getLogger(HackerRankTest.class);

	private SolutionFactory factory = new SolutionFactory();

	private Solution current;

	@Rule
	public TestName testName = new TestName();

	@Rule
	public TestSettings testRule = new TestSettings();

	@BeforeClass
	public static void beforeClass() {
		Logger.getRootLogger().setLevel(Level.INFO);
	}

	private void requiredFileCheck() throws IOException {
		log.debug("stdin/stdout file check - if missing create: " + testName.getMethodName());
		FileUtils.createFileIfNotExisting(current.getInFile());
		FileUtils.createFileIfNotExisting(current.getExpectedFile());
	}

	@Before
	public void setup() throws Exception {
		log.info("setup: " + testName.getMethodName());
		loadTest();
		requiredFileCheck();
		IOUtils.redirectIO(current.getInFile(), current.getResultFile());
	}

	@After
	public void check() {
		IOUtils.resetRedirectedIO();
		printNumOfDiffs();
		moveDone();
	}

	private void printNumOfDiffs() {
		try {
			int numOfDiffs = FileUtils.diffsResultExpected(current.getResultFile(), current.getExpectedFile());
			boolean ok = (numOfDiffs == 0);
			if (ok) {
				log.info(current + ": OK!");
			} else {
				log.warn("Num of differences: " + numOfDiffs + "/" + current.getExpectedFile().length());
			}
			Assert.assertTrue(ok);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private final void stopTesting(String currentlyRuningTest) {
		log.error("Missing annotation:" + currentlyRuningTest);
		System.exit(0);
	}

	public void loadTest() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		Method currentlyRuningTest = Class.forName(testRule.className).getDeclaredMethod(testRule.methodName);
		TestInfo ti = currentlyRuningTest.getAnnotation(TestInfo.class);
		if (ti != null) {
			if (ti.group().equals("") || ti.solutionClass().equals("")) {
				stopTesting(currentlyRuningTest.getName());
			}
			String canonical = String.format("%s.%s.%s", Solution.BASE_PACKAGE, ti.group(), ti.solutionClass());
			log.debug("Loading: " + canonical);
			current = factory.getSolution(canonical);
		} else {
			stopTesting(currentlyRuningTest.getName());
		}
	}

	public void moveDone() {
		Method[] methods = Progress.class.getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(TestInfo.class)) {
				String name = method.getAnnotation(TestInfo.class).solutionClass();
				String group = method.getAnnotation(TestInfo.class).group();
				String from = String.format("%s.%s", "com.hackerrank.java.progress", name);
				String to = String.format("%s.%s.%s", "com.hackerrank.java", group, name);
				if (method.getAnnotation(TestInfo.class).done()) {
					SolutionBuilder.moveSolution(from, to);
				}
			}
		}
	}

	public void runTest() {
		if (current != null) {
			log.info("Test: " + current.toString());
			current.execute();
			log.debug("Solution executed");
		}
	}

}
