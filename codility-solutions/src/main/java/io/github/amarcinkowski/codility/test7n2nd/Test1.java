package io.github.amarcinkowski.codility.test7n2nd;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Test1 extends Solution {
	public Test1(TestInfo ti) {
		super(ti);
	} /*
		 * overloaded log:
		 * 
		 * private static void log(Object msg) {} /*
		 */

	Scanner scanner;

	public int solution(int A, int B) {
		StringBuilder sb = new StringBuilder();
		String as = new String("" + A);
		String bs = new String("" + B);
		int maxlength = as.length() > bs.length() ? as.length() : bs.length();
		for (int i = 0; i < maxlength; i++) {
			if (i < as.length()) {
				sb.append(as.charAt(i));
			}
			if (i < bs.length()) {
				sb.append(bs.charAt(i));
			}
		}
		int result = Integer.parseInt(sb.toString());
		if (result > 100000000) {
			return -1;
		} else {
			return result;
		}
	}

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		System.out.println(solution(a, b));
	}

	// hr:
	// public static void main(String[] args) {new Solution().execute();}
}
