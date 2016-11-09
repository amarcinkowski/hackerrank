package io.github.amarcinkowski.codility.test7n;

import java.util.Arrays;
import java.util.Scanner;
import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class TestA extends Solution {
	Scanner scanner;

	public TestA(TestInfo ti) {
		super(ti);
	} /*
		 * overloaded log:
		 * 
		 * private static void log(String msg) {System.out.println(msg);} /*
		 */

	int counterE = 0;
	int counterD = 0;

	public int solution(int X, int[] A) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == X) {
				counterE++;
				log(i + " " + counterE);
			}
		}
		log("d");
		for (int j = A.length - 1; j > 0; j--) {
			if (A[j] != X) {
				counterD++;
			}
			if (counterD == counterE) {
				log("J" + j);
				return j;
			}
			if (A[j] != X) {
				counterE--;
			}
			log(j + " " + counterE + " " + counterD);
		}
		return -1;
	}

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		int[] a = readIntArray(line);
		int k = scanner.nextInt();
		log(Arrays.toString(a) + " " + k);
		System.out.println(solution(k, a));
	}

	// hr:
	// public static void main(String[] args) {new Solution().execute();}
}
