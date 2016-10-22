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

	@Before
	public void setup() throws Exception {
		logger.debug("setup: " + testName.getMethodName());
		try {
			loadTest();
		} catch (Exception e) {
			stopTesting();
		}
		IOUtils.redirectIO(FileUtils.getInFile(current.getName()), FileUtils.getResultFile(current.getName()));
	}

	@After
	public void check() {
		IOUtils.resetRedirectedIO();
		printNumOfDiffs();
	}

	private int getNumOfDiffs() throws IOException {
		return FileUtils.diffsResultExpected(FileUtils.getResultFile(current.getName()),
				FileUtils.getExpectedFile(current.getName()));
	}

	private void printNumOfDiffs() {
		try {
			if (getNumOfDiffs() == 0) {
				logger.info(current + ": OK!");
				Assert.assertTrue(true);
			} else {
				logger.warn("Expected and Result differs");
				Assert.fail();
			}
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private final void stopTesting() {
		logger.error("Missing annotation:" + getCurrentlyRunningTest());
		System.exit(0);
	}

	private void checkAnnotations(TestInfo ti, String testname) throws MissingAnnotationException {
		if (ti == null || ti.group().equals("") || ti.solutionClass().equals("")) {
			throw new MissingAnnotationException(testname);
		}
	}

	private Method getCurrentlyRunningTest() {
		try {
			return Class.forName(testRule.className).getDeclaredMethod(testRule.methodName);
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			return null;
		}
	}

	private TestInfo getTestInfo(Method currentlyRuningTest) throws MissingAnnotationException {
		TestInfo ti = currentlyRuningTest.getAnnotation(TestInfo.class);
		checkAnnotations(ti, currentlyRuningTest.getName());
		return ti;
	}

	static String getCanonical(TestInfo ti) {
		return getCanonical(Solution.BASE_PACKAGE, ti.group(), ti.solutionClass());
	}

	static String getCanonical(String packagebase, String packagename, String classname) {
		return String.format("%s.%s.%s", packagebase, packagename, classname);
	}

	public void loadTest()
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, MissingAnnotationException {
		Method currentlyRuningTest = getCurrentlyRunningTest();
		TestInfo ti = getTestInfo(currentlyRuningTest);
		String canonical = getCanonical(ti);
		current = factory.getSolution(canonical);
	}

	public void runTest() {
		if (current != null) {
			logger.info("Test: " + current.toString());
			current.execute();
			logger.debug("Solution executed");
		}
	}

}
