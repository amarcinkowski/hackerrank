package com.hackerrank.java.intro;

import java.util.Scanner;

import io.github.amarcinkowski.hackerrank.Solution;

public class JavaDatatypes extends Solution {

	public JavaDatatypes(String name) {
		super(name);
	}
	/* 
	 * COPY FROM LINE BELOW:
	
	private void log(String msg) {}
	/* */

	Scanner scanner;

	public void execute() {
		scanner = new Scanner(System.in); 
		int n = scanner.nextInt();
		for (int i = 0; i < n; i++) {

			try {
				long x = scanner.nextLong();
				System.out.println(x + " can be fitted in:");
				if (x >= -128 && x <= 127)
					System.out.println("* byte");
				if (x >= Short.MIN_VALUE && x <= Short.MAX_VALUE)
					System.out.println("* short");
				if (x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE)
					System.out.println("* int");
				if (x >= Long.MIN_VALUE && x <= Long.MAX_VALUE)
					System.out.println("* long");
			} catch (Exception e) {
				System.out.println(scanner.next() + " can't be fitted anywhere.");
			}

		}
	}

	public static void main(String[] args) {
		new Solution().execute();
	}
	
}
