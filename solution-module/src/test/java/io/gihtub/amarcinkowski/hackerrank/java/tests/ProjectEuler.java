package io.gihtub.amarcinkowski.hackerrank.java.tests;

import org.junit.Test;

import io.github.amarcinkowski.solutionframework.SolutionTestSuite;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class ProjectEuler extends SolutionTestSuite {

	@Test
	@TestInfo(platform = "hackerrank", domain = "java", group = "projecteuler", solutionClass = "MultiplesOf3and5", taskDescription = "Project Euler #1: Multiples of 3 and 5")
	public void euler1() {
		runTest();
	}
	
	@Test
	@TestInfo(platform = "hackerrank", domain = "java", group = "projecteuler", solutionClass = "SumSquareDifference", taskDescription = "Project Euler #6: Sum square difference")
	public void euler6() {
		runTest();
	}

}
