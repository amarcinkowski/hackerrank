package io.github.amarcinkowski.hackerrank.projecteuler;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class SumSquareDifference extends Solution {
	public SumSquareDifference(TestInfo ti) {
		super(ti);
	} /*
		 * overloaded log:
		 * 
		 * private void log(String msg) {} /*
		 */

	Scanner scanner;

	private long oneToNsum(long n) {
		long ret = n * (n + 1) / 2;
		log("[" + n + "]\t1+2+..=" + ret);
		return ret;
	}

	private long oneToNsumOfSq(long n) {
		long ret = n * (n + 1) * (2 * n + 1) / 6;
		log("[" + n + "]\t1+4+9+..=" + ret);
		return ret;
	}

	private long count(long n) {
		long oneToN = oneToNsum(n);
		long result = oneToN * oneToN - oneToNsumOfSq(n);
		return result;
	}

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		for (int i = 0; i < x; i++) {
			long result = count(scanner.nextInt());
			log("" + result);
			System.out.println(result);
		}
	}

	// hr:
	// public static void main(String[] args) {new Solution().execute();}
}
