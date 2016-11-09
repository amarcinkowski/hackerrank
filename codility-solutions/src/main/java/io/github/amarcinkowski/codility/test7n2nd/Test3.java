package io.github.amarcinkowski.codility.test7n2nd;

import java.util.Arrays;
import java.util.Scanner;
import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Test3 extends Solution {
	public Test3(TestInfo ti) {
		super(ti);
	} /*
		 * overloaded log:
		 * 
		 * private static void log(String msg) {} /*
		 */


	public int solution(int[] A, int M) {
		int max = 0;
		int counter = 0;
		for (int j = 1; j <= M; j++) {
			counter = 0;
			for (int i = 0; i < A.length; i++) {
				if ((A[i] - j) % M == 0) {
					log(j + " >" + A[i] + "(" + i + ")");
					counter++;
				}
			}
			max = counter > max ? counter : max;
		}
		return max;
	}

	Scanner scanner;
	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		int[] a = readIntArray(line);
		int m = scanner.nextInt();
		log(Arrays.toString(a) + " " + m);
		System.out.println(solution(a, m));
	}

	// hr:
	// public static void main(String[] args) {new Solution().execute();}
}
