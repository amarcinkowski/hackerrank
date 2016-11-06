package io.github.amarcinkowski.codility.lesson1.tests;

import org.junit.Test;

import io.github.amarcinkowski.solutionframework.SolutionTestSuite;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Iterations extends SolutionTestSuite {

/* example test:
	@Test
	@TestInfo(platform = "", domain = "", subdomain = "", classname = "", taskDescription = "")
	public void giveMeAName() {
		runTest();
	}
*/
	@Test
	@TestInfo(platform = "codility", domain = "lesson1", subdomain = "iterations", classname = "BinaryGap", taskDescription = "Find longest sequence of zeros in binary representation of an integer.")
	public void binaryGap() {
		runTest();
	}


}
