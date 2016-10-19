package com.hackerrank.java.intro;

import java.util.Scanner;

import com.hackerrank.java.Solution;

public class StdinStdout2 extends Solution {

	public StdinStdout2(String name) {
		super(name);
	}

	Scanner scanner;
	
	public void execute() {
		logger.debug("execute");
		scanner = new Scanner(System.in, "UTF-8"); 
		scanner.useDelimiter(System.getProperty("line.separator")); // \r\n on hackerrank 
		int i = scanner.nextInt();
		double d = scanner.nextDouble();
		String s = scanner.next();
		
		System.out.println("String: " + s);
		System.out.println("Double: " + d);
		System.out.println("Int: " + i);
		scanner.close();
	}

}
