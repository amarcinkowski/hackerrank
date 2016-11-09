package io.github.amarcinkowski.codility.arrays;

import java.util.Arrays;
import java.util.Scanner;
import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Cyclicrotation extends Solution {
public Cyclicrotation(TestInfo ti) {super(ti);}	/*
* overloaded log:

	private static void log(Object msg) {}
/* */

	
    public int[] solution(int[] A, int K) {
    	int[] a1 = new int[A.length];
    	log(a1.length);
    	for(int i = 0; i < A.length; i++) {
    		int index = (i + K) % A.length;
    		a1[index] = A[i];
    		log("i " + index);
    	}
    	log("out" + Arrays.toString(a1));
        return a1;
    }

	Scanner scanner;
	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		String s =scanner.nextLine();
		log("in " + s);
		int[] a = readIntArray(s);
		log("in2" + Arrays.toString(a));
		int k = scanner.nextInt();
		System.out.println(Arrays.toString(solution(a, k)));
	}

// hr:
//	public static void main(String[] args) {new Solution().execute();}
}
