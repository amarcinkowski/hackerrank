package io.github.amarcinkowski.hackerrank.javaintroduction;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class JavaCurrencyFormatter extends Solution {
	public JavaCurrencyFormatter(TestInfo ti) {
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
		scanner.useLocale(Locale.US); // without this input "12324.134" won't parse in e.g. POLAND
		double payment = scanner.nextDouble();
		scanner.close();

		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		String us = formatter.format(payment);
		formatter = NumberFormat.getCurrencyInstance(new Locale.Builder().setRegion("in").setLanguage("en").build());
		String india = formatter.format(payment);
		formatter = NumberFormat.getCurrencyInstance(Locale.CHINA);
		String china = formatter.format(payment);
		formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		String france = formatter.format(payment);

		System.out.println("US: " + us);
		System.out.println("India: " + india);
		System.out.println("China: " + china);
		System.out.println("France: " + france);
	}

	// hr:
	public static void main(String[] args) {
		new Solution().execute();
	}
}
