package io.gihtub.amarcinkowski.hackerrank.java.tests;

import org.junit.Test;

import io.gihtub.amarcinkowski.hackerrank.java.HackerRankTest;
import io.github.amarcinkowski.TestInfo;

public class ProjectEuler extends HackerRankTest {

	/*
	 *
	 * 
	 * @Test
	 * 
	 * @TestInfo(domain = "java", group = "progress", solutionClass = "",
	 * hackerrankDescription = "") public void JavaFactory() { runTest(); }
	 */

	@Test
	@TestInfo(domain = "java", group = "projecteuler", solutionClass = "MultiplesOf3and5", hackerrankDescription = "Project Euler #1: Multiples of 3 and 5", done = true)
	public void euler1() {
		runTest();
	}
	
	@Test
	@TestInfo(domain = "java", group = "progress", solutionClass = "SumSquareDifference", hackerrankDescription = "Project Euler #6: Sum square difference", done = true)
	public void euler6() {
		runTest();
	}

}
