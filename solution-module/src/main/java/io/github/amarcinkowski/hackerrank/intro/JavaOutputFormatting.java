package io.github.amarcinkowski.hackerrank.intro;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;

public class JavaOutputFormatting extends Solution {

	public JavaOutputFormatting(String name) {
		super(name);
	}
	
	Scanner scanner;
	
	public void execute() {
		scanner = new Scanner(System.in); 
		System.out.println("================================");
		for (int i = 0; i < 3; i++) {
			String s1 = scanner.next();
			int x = scanner.nextInt();
			System.out.printf("%-15s%03d\n", s1, x);
		}
		System.out.println("================================");
	}

}
