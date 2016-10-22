package io.github.amarcinkowski.hackerrank.intro;

import java.util.Scanner;

import io.github.amarcinkowski.hackerrank.Solution;

public class JavaStaticInitializerBlock extends Solution {
	public JavaStaticInitializerBlock(String name) {
		super(name);
	} /*
		 * COPY FROM LINE BELOW:
		 * 
		 * private static void log(String msg) {} /*
		 */

	static Scanner scanner;

	static int H, B;
	static boolean flag;

	static {
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

	public static void main(String[] args) {
		if (flag) {
			int area = B * H;
			System.out.print(area);
		}

	}// end of main

}
