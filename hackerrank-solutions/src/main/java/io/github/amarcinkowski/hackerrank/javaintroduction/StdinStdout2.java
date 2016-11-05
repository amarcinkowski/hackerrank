package io.github.amarcinkowski.hackerrank.javaintroduction;

import java.util.Locale;
import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class StdinStdout2 extends Solution {

	public StdinStdout2(TestInfo ti) {
		super(ti);
	}

	Scanner scanner;

	public void execute() {
		scanner = new Scanner(System.in, "UTF-8");
		// "\r\n" on hackerrank
		scanner.useDelimiter(System.getProperty("line.separator"));
		scanner.useLocale(new Locale.Builder().setLanguage("pl").setRegion("PL").build());
		try {
			int i = scanner.nextInt();
			double d = scanner.nextDouble();
			String s = scanner.next();

			System.out.println("String: " + s);
			System.out.println("Double: " + d);
			System.out.println("Int: " + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		scanner.close();
	}

}
