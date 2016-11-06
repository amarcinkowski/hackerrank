package io.github.amarcinkowski.codility.java.tests;

import org.junit.Test;

import io.github.amarcinkowski.solutionframework.SolutionTestSuite;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class DemoTest extends SolutionTestSuite {

	@Test
	@TestInfo(platform = "codility", domain = "java", subdomain = "demo-test", classname = "Equi", taskDescription = "Find an index in an array such that its prefix sum equals its suffix sum.")
	public void equi() {
		runTest();
	}

	@Test
	@TestInfo(platform = "codility", domain = "java", subdomain = "demo-test", classname = "Test2", taskDescription = "")
	public void test2() {
		runTest();
	}


}
