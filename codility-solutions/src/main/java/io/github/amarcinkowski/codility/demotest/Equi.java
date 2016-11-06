package io.github.amarcinkowski.codility.demotest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

// Equi: Find an index in an array such that its prefix sum equals its suffix sum.


/**
 * FAIL ON
 * 
 * extreme_large_numbers 
Sequence with extremely large numbers testing arithmetic overflow. ✘WRONG ANSWER 
got 2, but it is not equilibrium point, sum[0..1]=4294967294, sum[3..3]=-2
▶ extreme_negative_numbers 
Sequence with extremely large numbers testing arithmetic overflow. ✘WRONG ANSWER 
got 0, but it is not equilibrium point, left sum (empty set)=0, sum[1..2]=-4294967296
▶ overflow_tests1 
arithmetic overflow tests ✘WRONG ANSWER 
got 0, but it is not equilibrium point, left sum (empty set)=0, sum[1..2]=-4294967296
▶ overflow_tests2 
arithmetic overflow tests ✘WRONG ANSWER 
got 2, but it is not equilibrium point, sum[0..1]=-4294967296, right sum (empty set)=0
 * 
 */
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

	private int[] readArray() {
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		List<Integer> l = Arrays.asList(line.split(" ")).stream().map(Integer::valueOf).collect(Collectors.toList());
		return l.stream().mapToInt(i -> i).toArray();
	}
	
	public int solution(int[] A) {
		Integer[] iA = Arrays.stream(A).boxed().toArray(Integer[]::new);
		int sum = Arrays.asList(iA).stream().mapToInt(i -> i).sum();
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
		int[] a = readArray();
		int i = solution(a);
		log(i);
		System.out.println(i);
	}

	// hr:
	public static void main(String[] args) {
		new Solution().execute();
	}
}
