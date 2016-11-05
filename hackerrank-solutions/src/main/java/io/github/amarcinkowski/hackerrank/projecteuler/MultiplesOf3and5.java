package io.github.amarcinkowski.hackerrank.projecteuler;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class MultiplesOf3and5 extends Solution {
	public MultiplesOf3and5(TestInfo ti) {
		super(ti);
	} /*
		 * overloaded log:
		 * 
		 * private void log(String msg) {} /*
		 */

	Scanner scanner;

	private long closestLowerMultiple(int n, int k) {
		sb.append(" Biggest multiple of " + k + " under " + n);
		do {
			n--;
		} while (n % k != 0);
		sb.append(" is " + n);
		return n;
	}

	private long oneToNsum(long n) {
		long ret = n * (n + 1) / 2;
		sb.append("\t1+2+..+" + n + "=" + ret);
		return ret;
	}

	private long find(int n) {
		long clm3 = closestLowerMultiple(n, 3);
		long multiples3 = 3 * oneToNsum(clm3 / 3);
//		sb.append("\t3+6+9+...+" + clm3 + "=" + multiples3);
		long clm5 = closestLowerMultiple(n, 5);
		long multiples5 = 5 * oneToNsum(clm5 / 5);
//		sb.append("\t5+10+...+" + clm5 + "=" + multiples5);
		long clm15 = closestLowerMultiple(n, 15);
		long multiples15 = 15 * oneToNsum(clm15 / 15);
//		sb.append("\t15+30+...+" + clm15 + "=" + multiples15);
		long sum = multiples3 + multiples5 - multiples15;
		sb.append("\tsum=" + sum);
		log(sb.toString());
		return sum;
	} 

	StringBuilder sb;

	public long findMO3a5(int n) {
		sb = new StringBuilder();
		return find(n);
	}

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		for (int i = 0; i < x; i++) {
			int n = scanner.nextInt();
			System.out.println(findMO3a5(n));
		}
	}

	// hr:
	// public static void main(String[] args) {new Solution().execute();}
}
