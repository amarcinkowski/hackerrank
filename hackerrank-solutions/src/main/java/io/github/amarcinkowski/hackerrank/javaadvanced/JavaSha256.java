package io.github.amarcinkowski.hackerrank.javaadvanced;

import java.util.Scanner;
import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class JavaSha256 extends Solution {
public JavaSha256(TestInfo ti) {super(ti);}	/*
* overloaded log:

	private static void log(String msg) {}
/* */

	Scanner scanner;

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		int x = scanner.nextInt();
	}

// hr:
	public static void main(String[] args) {new Solution().execute();}
}
