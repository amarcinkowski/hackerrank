package io.github.amarcinkowski.codility.time_complexity;

import java.util.Scanner;
import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Tapeequilibrium extends Solution {
public Tapeequilibrium(TestInfo ti) {super(ti);}	/*
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
