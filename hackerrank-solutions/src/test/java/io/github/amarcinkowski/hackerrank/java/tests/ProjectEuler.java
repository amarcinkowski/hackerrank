package io.github.amarcinkowski.hackerrank.java.tests;

import org.junit.Test;

import io.github.amarcinkowski.solutionframework.SolutionTestSuite;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class ProjectEuler extends SolutionTestSuite {

	@Test
	@TestInfo(platform = "hackerrank", domain = "java", subdomain = "projecteuler", classname = "MultiplesOf3and5", taskDescription = "Project Euler #1: Multiples of 3 and 5")
	public void e1multiple3and5() {
		runTest();
	}
	
	@Test
	@TestInfo(platform = "hackerrank", domain = "java", subdomain = "projecteuler", classname = "SumSquareDifference", taskDescription = "Project Euler #6: Sum square difference")
	public void e6sumSqDiff() {
		runTest();
	}

}
