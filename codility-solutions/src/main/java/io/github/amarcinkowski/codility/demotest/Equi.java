package io.github.amarcinkowski.codility.demotest;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

// Equi: 


public class Equi extends Solution {
	public Equi(TestInfo ti) {
		super(ti);
	}
	/*
	 * overloaded log:
	 * 
	 * private static void log(String msg) {} /*
	 */

	Scanner scanner;

	public int solution(int[] A) {
		long sum = 0;
		for(int i : A) {
			sum += i;
		}
		//System.out.println(sum);
		long left = 0;
		long right = sum;
		for(int p = 0; p < A.length; p++) {
		    right -= A[p];
		    //System.out.println(p + ": " + left + " " + right);
		    if (left == right) {
		        return p;
		    }
		    left += A[p];
		}
		return -1;
	}

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		int[] a = readIntArray(line);
		int i = solution(a);
		log(i);
		System.out.println(i);
	}

	// hr:
	public static void main(String[] args) {
//		new Solution().execute();
		int i = 1082132608;
		int j = 1082132608;
		long x = 0;
		x += i;
		x += j;
		System.out.println(x);
	}
}
