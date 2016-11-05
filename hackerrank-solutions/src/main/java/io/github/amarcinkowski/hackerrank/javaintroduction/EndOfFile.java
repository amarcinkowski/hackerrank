package io.github.amarcinkowski.hackerrank.javaintroduction;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class EndOfFile extends Solution {
	public EndOfFile(TestInfo ti) {
		super(ti);
	} /*
		 * COPY FROM LINE BELOW:
		 * 
		 * private void log(String msg) {} /*
		 */

	Scanner scanner;

	public void execute() {
		scanner = new Scanner(System.in);
		int i = 0;
		while (scanner.hasNextLine()) {
			String l = scanner.nextLine();
			String s = String.format("%d %s", ++i, l) + (scanner.hasNextLine() ? "\n" : "");
			System.out.print(s);
		}
	}

	public static void main(String[] args) {new Solution().execute();}
}
