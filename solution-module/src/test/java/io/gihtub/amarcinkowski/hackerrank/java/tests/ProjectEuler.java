package io.gihtub.amarcinkowski.hackerrank.java.tests;

import org.junit.Test;

import io.github.amarcinkowski.hackerrank.SolutionTestSuite;
import io.github.amarcinkowski.hackerrank.TestInfo;

public class ProjectEuler extends SolutionTestSuite {

	@Test
	@TestInfo(domain = "java", group = "projecteuler", solutionClass = "MultiplesOf3and5", hackerrankDescription = "Project Euler #1: Multiples of 3 and 5", done = true)
	public void euler1() {
		runTest();
	}
	
	@Test
	@TestInfo(domain = "java", group = "projecteuler", solutionClass = "SumSquareDifference", hackerrankDescription = "Project Euler #6: Sum square difference", done = true)
	public void euler6() {
		runTest();
	}

}
