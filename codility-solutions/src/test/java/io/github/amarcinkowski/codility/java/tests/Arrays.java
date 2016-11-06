package io.github.amarcinkowski.codility.java.tests;

import org.junit.Test;

import io.github.amarcinkowski.solutionframework.SolutionTestSuite;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Arrays extends SolutionTestSuite {

/* example test:
	@Test
	@TestInfo(platform = "", domain = "", subdomain = "", classname = "", taskDescription = "")
	public void giveMeAName() {
		runTest();
	}
*/
	@Test
	@TestInfo(platform = "codility", domain = "java", subdomain = "2-arrays", classname = "Oddoccurrencesinarray", taskDescription = "OddOccurrencesInArray")
	public void oddoccurrencesinarray() {
		runTest();
	}

	@Test
	@TestInfo(platform = "codility", domain = "java", subdomain = "2-arrays", classname = "Cyclicrotation", taskDescription = "CyclicRotation")
	public void cyclicrotation() {
		runTest();
	}


}
