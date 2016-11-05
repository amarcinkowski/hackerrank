package io.github.amarcinkowski.hackerrank.javaintroduction;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class IfElse extends Solution {

	public IfElse(TestInfo ti) {
		super(ti);
	}
	
	Scanner scanner;

	public void execute() {
		scanner = new Scanner(System.in); 
		int n = scanner.nextInt();
		String ans = "";
		if (n % 2 == 1) {
			ans = "Weird";
		} else {
			if (n < 6 || n > 20) {
				ans = "Not Weird";
			} else {
				ans = "Weird";
			}

		}
		System.out.println(ans);

	}
}
