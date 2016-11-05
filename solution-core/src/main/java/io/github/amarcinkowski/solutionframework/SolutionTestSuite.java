package io.github.amarcinkowski.solutionframework;

import java.io.IOException;
import java.lang.reflect.Method;

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
import io.github.amarcinkowski.utils.StringUtils;

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
		System.out.println(current.in().getAbsolutePath());
		System.out.println(current.in().exists());
		System.out.println(current.out().getAbsolutePath());
		System.out.println(current.out().exists());
		IOUtils.redirectIO(current.in(),current.out());
	}

	@After
	public void check() {
		IOUtils.resetRedirectedIO();
		printNumOfDiffs();
	}

	private int getNumOfDiffs() throws IOException {
		return FileUtils.diffsResultExpected(current.out(), current.expected());
	}

	private void printNumOfDiffs() {
		try {
			if (getNumOfDiffs() == 0) {
				logger.info(String.format("\t OK! (%s) ???? USE NAME?",  current.getClassName()));
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
		String packageBase = String.format("%s.%s", Solution.PACKAGE, ti.platform());
		String canonical = getCanonical(packageBase, StringUtils.packagify(ti.group()), ti.solutionClass());
		System.out.println("CANONICAL>" + canonical);
		return canonical;
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
			logger.trace("Test: " + current.toString());
			current.execute();
			logger.trace("Test done");
		}
	}

}
