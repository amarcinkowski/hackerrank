package io.github.amarcinkowski.codility.test7n2nd;

import java.util.Arrays;
import java.util.Scanner;
import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Test2 extends Solution {
	public Test2(TestInfo ti) {
		super(ti);
	} /*
		 * overloaded log:
		 * 
		 * private static void log(String msg) {} /*
		 */


	private int up(int a, int b) {
		if (b > a) {
			return 1;
		} else {
			return 0;
		}
	}

	private int down(int a, int b) {
		if (b < a) {
			return -1;
		} else {
			return 0;
		}
	}

	public int solution(int[] A) {
		if (A.length == 0) {
			return 0;
		}
		if (A.length == 1) {
			return 1;
		}
		// if a.length == 1 return 0
		int count = 1;
		int max = 0;
		int result = 0;
		int previous = 1;
		for (int i = 1; i < A.length; i++) {
			int a = A[i - 1];
			int b = A[i];
			result = up(a, b) == 1 ? 1 : down(a, b) == -1 ? -1 : 0;
			// first
			if (i == 1) {
				previous = -result;
			}
			// not-osciliating
			if (result != -previous) {
				count = 1;
			}
			// osciliating
			if (result != 0) {
				count++;
			}
			max = count > max ? count : max;
			previous = result;
			log(result + "," + previous + " " + count + " " + max);
		}
		return max;
	}

	Scanner scanner;
	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		int[] a = readIntArray(line);
		log(Arrays.toString(a));
		System.out.println(solution(a));
	}

	// hr:
	// public static void main(String[] args) {new Solution().execute();}
}
