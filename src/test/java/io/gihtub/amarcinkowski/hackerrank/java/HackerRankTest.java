package io.gihtub.amarcinkowski.hackerrank.java;

import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.hackerrank.Solution;
import io.github.amarcinkowski.hackerrank.SolutionFactory;
import io.github.amarcinkowski.hackerrank.TestInfo;
import io.github.amarcinkowski.utils.FileUtils;
import io.github.amarcinkowski.utils.IOUtils;

public class HackerRankTest {

	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(HackerRankTest.class);

	private SolutionFactory factory = new SolutionFactory();

	private Solution current;

	@Rule
	public TestName testName = new TestName();

	@Rule
	public TestSettings testRule = new TestSettings();

	@BeforeClass
	public static void beforeClass() {
	}

	private void requiredFileCheck() throws IOException {
		logger.debug("stdin/stdout file check - if missing create: " + testName.getMethodName());
		FileUtils.createFileIfNotExisting(current.getInFile());
		FileUtils.createFileIfNotExisting(current.getExpectedFile());
	}

	@Before
	public void setup() throws Exception {
		logger.info("setup: " + testName.getMethodName());
		loadTest();
		requiredFileCheck();
		IOUtils.redirectIO(current.getInFile(), current.getResultFile());
	}

	@After
	public void check() {
		IOUtils.resetRedirectedIO();
		printNumOfDiffs();
	}

	private void printNumOfDiffs() {
		try {
			int numOfDiffs = FileUtils.diffsResultExpected(current.getResultFile(), current.getExpectedFile());
			boolean ok = (numOfDiffs == 0);
			if (ok) {
				logger.info(current + ": OK!");
			} else {
				logger.warn("Num of differences: " + numOfDiffs + "/" + current.getExpectedFile().length());
			}
			Assert.assertTrue(ok);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private final void stopTesting(String currentlyRuningTest) {
		logger.error("Missing annotation:" + currentlyRuningTest);
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
			logger.debug("Loading: " + canonical);
			current = factory.getSolution(canonical);
		} else {
			stopTesting(currentlyRuningTest.getName());
		}
	}

	public void runTest() {
		if (current != null) {
			logger.info("Test: " + current.toString());
			current.execute();
			logger.debug("Solution executed");
		}
	}

}
