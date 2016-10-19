package io.github.amarcinkowski.hackerrank.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import io.github.amarcinkowski.hackerrank.Solution;

interface performOperation {
	int check(int a);
}

class Math {
	

	public static int checker(performOperation p, int num) {
		return p.check(num);
	}
	
	private static boolean isPrime(int num) {
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

	private static boolean isPalindrome(int num) {
		String s1 = "" + num;
		String s2 = new StringBuilder().append(num).reverse().toString();
		return s1.equals(s2);
	}


	public performOperation checkEvenOdd() {
		return num -> (num % 2 == 0 ? 0 : 1);
	}

	public performOperation checkPrime() {
		return num -> (isPrime(num) ? 0 : 1);
	}

	public performOperation checkPalindrome() {
		return num -> (isPalindrome(num) ? 0 : 1);
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
			performOperation op;
			int ret = 0;
			String ans = null;
			while (T-- > 0) {
				String s = br.readLine().trim();
				StringTokenizer st = new StringTokenizer(s);
				int ch = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				if (ch == 1) {
					op = ob.checkEvenOdd();
					ret = ob.checker(op, num);
					ans = (ret == 0) ? "EVEN" : "ODD";
				} else if (ch == 2) {
					op = ob.checkPrime();
					ret = ob.checker(op, num);
					ans = (ret == 0) ? "PRIME" : "COMPOSITE";
				} else if (ch == 3) {
					op = ob.checkPalindrome();
					ret = ob.checker(op, num);
					ans = (ret == 0) ? "PALINDROME" : "NOT PALINDROME";

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
