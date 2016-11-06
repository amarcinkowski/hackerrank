package io.github.amarcinkowski.solutionframework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.solutionframework.exception.MissingAnnotationException;
import io.github.amarcinkowski.utils.FileUtils;
import io.github.amarcinkowski.utils.IOUtils;

public class SolutionTestSuite {

	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(SolutionTestSuite.class);

	private SolutionFactory factory = new SolutionFactory();

	private Solution current;

	@Rule
	public TestName testName = new TestName();

	@Rule
	public SolutionTestSettings testRule = new SolutionTestSettings();

	@BeforeClass
	public static void beforeClass() {
	}

	@Before
	public void setup() throws Exception {
		logger.trace("setup: " + testName.getMethodName());
		try {
			loadTest();
		} catch (Exception e) {
			stopTesting();
		}
		logger.trace(current.in().getAbsolutePath() + current.in().exists());
		logger.trace(current.out().getAbsolutePath() + current.out().exists());
		IOUtils.redirectIO(current.in(), current.out());
	}

	@After
	public void check() {
		IOUtils.resetRedirectedIO();
		printNumOfDiffs();
	}

	private int getNumOfDiffs() throws IOException {
		int diffs = FileUtils.diffsResultExpected(current.out(), current.expected());
		Assert.assertEquals(new String(Files.readAllBytes(current.expected().toPath())),
				new String(Files.readAllBytes(current.out().toPath())));
		return diffs;
	}

	private void printNumOfDiffs() {
		try {
			if (getNumOfDiffs() == 0) {
				logger.info(String.format(" |= OK! (%s)", current.getClassname()));
				Assert.assertTrue(true);
			} else {
				logger.warn(" |= Expected and Result differs");
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
		if (ti == null || ti.subdomain().equals("") || ti.classname().equals("")) {
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

	// static String getCanonical(TestInfo ti) {
	// String packageBase = String.format("%s.%s", Solution.PACKAGE,
	// ti.platform());
	// String canonical = getCanonical(packageBase,
	// StringUtils.packagify(ti.group()), ti.solutionClass());
	// System.out.println("CANONICAL>" + canonical);
	// return canonical;
	// }

	static String getCanonical(String packagebase, String packagename, String classname) {
		return String.format("%s.%s.%s", packagebase, packagename, classname);
	}

	public void loadTest()
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, MissingAnnotationException {
		Method currentlyRuningTest = getCurrentlyRunningTest();
		TestInfo ti = getTestInfo(currentlyRuningTest);
		// String canonical = getCanonical(ti);
		current = factory.getSolution(ti);
	}

	public void runTest() {
		if (current != null) {
			logger.trace("Test: " + current.toString());
			current.execute();
			logger.trace("Test done");
		}
	}

}
