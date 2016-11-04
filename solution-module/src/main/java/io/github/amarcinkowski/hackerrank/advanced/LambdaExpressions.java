package io.github.amarcinkowski.hackerrank.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import io.github.amarcinkowski.solutionframework.Solution;

interface PerformOperation {
	boolean check(int a);
}

class Math {
	

	public static boolean checker(PerformOperation p, int num) {
		return p.check(num);
	}
	
	private static boolean prime(int num) {
		if (num < 2)
			return false;
		if (num == 2)
			return true;
		if (num % 2 == 0)
			return false;
		for (int i = 3; i * i <= num; i += 2)
			if (num % i == 0)
				return false;
		return true;
	}

	private static boolean palindrome(int num) {
		String s1 = "" + num;
		String s2 = new StringBuilder().append(num).reverse().toString();
		return s1.equals(s2);
	}


	public PerformOperation is_odd() {
		return num -> (num % 2 == 1);
	}

	public PerformOperation is_prime() {
		return num -> (prime(num));
	}

	public PerformOperation is_palindrome() {
		return num -> (palindrome(num));
	}
}

public class LambdaExpressions extends Solution {
	public LambdaExpressions(String name) {
		super(name);
	} /*
		 * overloaded log:
		 * 
		 * private void log(String msg) {} /*
		 */

	Scanner scanner;

	@SuppressWarnings("static-access")
	public void execute() {
		log("execute");
		Math ob = new Math();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		try {
			T = Integer.parseInt(br.readLine());
			PerformOperation op;
			boolean ret = false;
			String ans = null;
			while (T-- > 0) {
				String s = br.readLine().trim();
				StringTokenizer st = new StringTokenizer(s);
				int ch = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				if (ch == 1) {
					op = ob.is_odd();
					ret = ob.checker(op, num);
					ans = (ret) ? "EVEN" : "ODD";
				} else if (ch == 2) {
					op = ob.is_prime();
					ret = ob.checker(op, num);
					ans = (ret) ? "PRIME" : "COMPOSITE";
				} else if (ch == 3) {
					op = ob.is_palindrome();
					ret = ob.checker(op, num);
					ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

				}
				System.out.println(ans);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	// hr:
	// public static void main(String[] args) {new Solution().execute();}
}
