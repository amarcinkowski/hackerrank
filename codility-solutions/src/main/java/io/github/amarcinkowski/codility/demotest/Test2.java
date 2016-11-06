package io.github.amarcinkowski.codility.demotest;

import java.util.Scanner;
import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Test2 extends Solution {
public Test2(TestInfo ti) {super(ti);}	/*
* overloaded log:

	private static void log(String msg) {}
/* */

	Scanner scanner;

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		log("in " + x + " out " + (x + 1));
		System.out.println(x + 1);
	}

// hr:
	public static void main(String[] args) {new Solution().execute();}
}
