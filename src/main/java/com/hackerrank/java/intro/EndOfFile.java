package com.hackerrank.java.intro;

import java.util.Scanner;
import com.hackerrank.java.Solution;

public class EndOfFile extends Solution {
	public EndOfFile(String name) {
		super(name);
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

	// public static void main(String[] args) {new Solution().execute();}
}
