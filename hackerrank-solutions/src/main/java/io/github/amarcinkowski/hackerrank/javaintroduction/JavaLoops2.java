package io.github.amarcinkowski.hackerrank.javaintroduction;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;

public class JavaLoops2 extends Solution {

	public JavaLoops2(String name) {
		super(name);
	}

    protected static void log(String log) {}
	static Scanner scanner;

	public void execute() {
		scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		log("Scan lines: " + t);
		for (int i = 0; i < t; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int n = scanner.nextInt();
			compute(a, b, n);
		}
	}

	private static void compute(int a, int b, int n) {
		log("a" + a + " b" + b + " n" + n);
		int result = 0;
		StringBuilder resultString = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			result = a + ((int) Math.pow(2, i) - 1) * b;
			resultString.append(result);
			resultString.append(" ");
		}
		resultString.replace(resultString.length() - 1, resultString.length(), "");
		System.out.println(resultString.toString());
		log(resultString.toString());
	}
    
// hr:
	public static void main(String[] args) {new Solution().execute();}

}
