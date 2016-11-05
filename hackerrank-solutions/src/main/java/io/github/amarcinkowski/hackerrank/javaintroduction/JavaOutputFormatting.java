package io.github.amarcinkowski.hackerrank.javaintroduction;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class JavaOutputFormatting extends Solution {

	public JavaOutputFormatting(TestInfo ti) {
		super(ti);
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
