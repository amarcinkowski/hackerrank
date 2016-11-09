package io.github.amarcinkowski.codility.java.tests;

import org.junit.Test;

import io.github.amarcinkowski.solutionframework.SolutionTestSuite;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Time_complexity extends SolutionTestSuite {

/* example test:
	@Test
	@TestInfo(platform = "", domain = "", subdomain = "", classname = "", taskDescription = "")
	public void giveMeAName() {
		runTest();
	}
*/
	@Test
	@TestInfo(platform = "codility", domain = "java", subdomain = "3-time_complexity", classname = "Tapeequilibrium", taskDescription = "TapeEquilibrium")
	public void tapeequilibrium() {
		runTest();
	}

	@Test
	@TestInfo(platform = "codility", domain = "java", subdomain = "3-time_complexity", classname = "Frogjmp", taskDescription = "FrogJmp")
	public void frogjmp() {
		runTest();
	}

	@Test
	@TestInfo(platform = "codility", domain = "java", subdomain = "3-time_complexity", classname = "Permmissingelem", taskDescription = "PermMissingElem")
	public void permmissingelem() {
		runTest();
	}


}
