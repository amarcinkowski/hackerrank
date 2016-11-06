package io.github.amarcinkowski.codility.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Oddoccurrencesinarray extends Solution {
	public Oddoccurrencesinarray(TestInfo ti) {
		super(ti);
	} /*
		 * } /* overloaded log:
		 * 
		 * private static void log(String msg) {} /*
		 */

	Scanner scanner;

	int solution(int[] a) {
		Arrays.sort(a);
		// if single element
		if (a.length == 1) {
			return a[0];
		}
		// std
		for (int i = 0; i < a.length - 1; i += 2) {
			if (a[i] != a[i + 1]) {
				return a[i];
			}
		}
		// if array has odd size & lone last element
		return a[a.length - 1];
	}

	private int[] readArray() {
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		List<Integer> l = Arrays.asList(line.split(" ")).stream().map(Integer::valueOf).collect(Collectors.toList());
		return l.stream().mapToInt(i -> i).toArray();
	}

	public void execute() {
		log("execute");
		int[] array = readArray();
		int i = solution(array);
		log("res: " + i);
		System.out.println(i);
	}

	// hr:
	public static void main(String[] args) {
		new Solution().execute();
	}
}
