package io.github.amarcinkowski.hackerrank.javaintroduction;

import java.util.Scanner;
import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class JavaIntToString extends Solution {
	public JavaIntToString(TestInfo ti) {
		super(ti);
	} /*
		 * overloaded log:
		 * 
		 * private static void log(String msg) {} /*
		 */

	Scanner scanner;

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		String s = String.format("%d", n);
		log(n == Integer.parseInt(s));
		System.out.println(s);
	}

	// hr:
	public static void main(String[] args) {
		new Solution().execute();
	}
}
