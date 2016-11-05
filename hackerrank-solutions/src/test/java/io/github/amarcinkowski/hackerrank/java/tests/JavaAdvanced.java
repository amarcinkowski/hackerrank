package io.github.amarcinkowski.hackerrank.java.tests;

import org.junit.Test;

import io.github.amarcinkowski.solutionframework.SolutionTestSuite;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class JavaAdvanced extends SolutionTestSuite {

	@Test
	@TestInfo(platform = "hackerrank", domain = "java", group = "java-advanced", solutionClass = "CanYouAccess", taskDescription = "Can You Access?")
	public void canYouAccess() {
		runTest();
	}

	@Test
	@TestInfo(platform = "hackerrank", domain = "java", group = "java-advanced", solutionClass = "SimpleAddition", taskDescription = "Java Varargs - Simple Addition")
	public void simpleAddition() {
		runTest();
	}

	@Test
	@TestInfo(platform = "hackerrank", domain = "java", group = "java-advanced", solutionClass = "PrimeChecker", taskDescription = "Prime Checker")
	public void primeChecker() {
		runTest();
	}

	@Test
	@TestInfo(platform = "hackerrank", domain = "java", group = "java-advanced", solutionClass = "ReflectionAttributes", taskDescription = "Java Reflection - Attributes")
	public void reflectionAttributes() {
		runTest();
	}

	@Test
	@TestInfo(platform = "hackerrank", domain = "java", group = "java-advanced", solutionClass = "JavaFactory", taskDescription = "Java Factory")
	public void javaFactory() {
		runTest();
	}

	@Test
	@TestInfo(platform = "hackerrank", domain = "java", group = "java-advanced", solutionClass = "Singleton", taskDescription = "Java Singleton")
	public void singleton() {
		runTest();
	}

	@Test
	@TestInfo(platform = "hackerrank", domain = "java", group = "java-advanced", solutionClass = "Annotations", taskDescription = "Java Annotations")
	public void annotations() {
		runTest();
	}

	@Test
	@TestInfo(platform = "hackerrank", domain = "java", group = "java-advanced", solutionClass = "CovariantReturnTypes", taskDescription = " Covariant Return Types")
	public void covariantReturnTypes() {
		runTest();
	}

	@Test
	@TestInfo(platform = "hackerrank", domain = "java", group = "java-advanced", solutionClass = "LambdaExpressions", taskDescription = "Java Lambda Expressions")
	public void lambdaExpressions() {
		runTest();
	}

}
