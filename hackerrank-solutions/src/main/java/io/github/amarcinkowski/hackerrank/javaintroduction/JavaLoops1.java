package io.github.amarcinkowski.hackerrank.javaintroduction;

import java.util.Scanner;
import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class JavaLoops1 extends Solution {
public JavaLoops1(TestInfo ti) {super(ti);}	/*
* overloaded log:

	private static void log(String msg) {}
/* */

	Scanner scanner;

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		int N = scanner.nextInt();
        long result = 0;
		for(int i = 1; i <= 10; i++) {
            result += N; 
			System.out.println(String.format("%d x %d = %d", N, i, result));
		}
	}

// hr:
	public static void main(String[] args) {new Solution().execute();}
}
