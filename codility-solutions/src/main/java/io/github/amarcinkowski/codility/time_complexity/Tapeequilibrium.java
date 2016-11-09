package io.github.amarcinkowski.codility.time_complexity;

import java.util.Scanner;
import java.util.stream.IntStream;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Tapeequilibrium extends Solution {
	public Tapeequilibrium(TestInfo ti) {
		super(ti);
	} /*
		 * overloaded log:
		 * 
		 * private static void log(Object msg) {} /*
		 */

	public int solution(int[] A) {
		int localMin = Integer.MAX_VALUE;
		int sumA = 0;
		int sum = IntStream.of(A).sum();
		log(sum);
		for (int i = 1; i < A.length; i++) {
			sumA += A[i - 1];
			sum -= A[i - 1];
			int result = Math.abs(sumA - sum);
			log(sumA + " " + sum + " " + result);
			if (result < localMin) {
				localMin = result;
			}
		}
		log(sum);
		return localMin;
	}

	Scanner scanner;

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		int[] x = readIntArray(scanner.nextLine());
		System.out.println(solution(x));
	}

	// hr:
	// public static void main(String[] args) {new Solution().execute();}
}
