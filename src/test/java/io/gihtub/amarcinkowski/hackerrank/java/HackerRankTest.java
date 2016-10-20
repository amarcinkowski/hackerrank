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
		loadTest();
		IOUtils.redirectIO(FileUtils.getInFile(current.getName()), FileUtils.getResultFile(current.getName()));
	}

	@After
	public void check() {
		IOUtils.resetRedirectedIO();
		printNumOfDiffs();
	}

	private void printNumOfDiffs() {
		try {
			int numOfDiffs = FileUtils.diffsResultExpected(FileUtils.getResultFile(current.getName()),
					FileUtils.getExpectedFile(current.getName()));
			boolean ok = (numOfDiffs == 0);
			if (ok) {
				logger.info(current + ": OK!");
			} else {
				logger.warn("Num of differences: " + numOfDiffs + "/"
						+ FileUtils.getExpectedFile(current.getName()).length());
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

	private void checkAnnotations(TestInfo ti, String testname) throws MissingAnnotationException {
		if (ti == null || ti.group().equals("") || ti.solutionClass().equals("")) {
			throw new MissingAnnotationException(testname);
		}
	}

	private Method getCurrentlyRunningTest() throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		return Class.forName(testRule.className).getDeclaredMethod(testRule.methodName);
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
