package com.hackerrank.java.intro;

import java.util.Scanner;

import io.github.amarcinkowski.hackerrank.Solution;

public class IfElse extends Solution {

	public IfElse(String name) {
		super(name);
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
