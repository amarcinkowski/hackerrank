package io.github.amarcinkowski.codility.iterations;

import java.util.Scanner;
import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class BinaryGap extends Solution {
	public BinaryGap(TestInfo ti) {
		super(ti);
	} /* overloaded log:
		  
		  private static void log(String msg) {} /*
		 */

	Scanner scanner;
	
	public int solution(int N) {
        int count = 0;
		int max = 0;
		for (char c : Integer.toBinaryString(N).toCharArray()) {
			if (c == '0') {
				count++;
				log("0 " + count);
			} else if (c == '1') {
				log("1 " + count);
				max = (max < count ? count : max);
				count = 0;
			}
		}
		return max;
    }

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		log(Integer.toBinaryString(x));
		
		System.out.println(solution(x));
	}

	// hr:
	public static void main(String[] args) {
		new Solution().execute();
	}
}
