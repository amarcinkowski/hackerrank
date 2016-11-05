package io.github.amarcinkowski.hackerrank.javaintroduction;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class JavaStaticInitializerBlock extends Solution {
	public JavaStaticInitializerBlock(TestInfo ti) {
		super(ti);
	} /*
		 * COPY FROM LINE BELOW:
		 * 
		 * private void log(String msg) {} /*
		 */

	Scanner scanner;

	static int H, B;
	static boolean flag;

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		H = scanner.nextInt();
		B = scanner.nextInt();
		if (B <= 0 || H <= 0) {
			flag = false;
			System.out.println("java.lang.Exception: Breadth and height must be positive");
		} else {
			log("" + H * B);
			System.out.print(H * B);
		}
	}

	// uncomment in hr:
	// static { new Solution().execute(); }

}
