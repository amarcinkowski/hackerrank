package io.github.amarcinkowski.codility.java.tests;

import org.junit.Test;

import io.github.amarcinkowski.solutionframework.SolutionTestSuite;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class DemoTest extends SolutionTestSuite {

	@Test
	@TestInfo(platform = "codility", domain = "java", subdomain = "demotest", classname = "Equi", taskDescription = "CyclicRotation")
	public void equi() {
		runTest();
	}


}
