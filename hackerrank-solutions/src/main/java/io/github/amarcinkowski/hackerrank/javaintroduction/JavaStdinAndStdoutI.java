package io.github.amarcinkowski.hackerrank.javaintroduction;

import java.util.Scanner;
import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class JavaStdinAndStdoutI extends Solution {
public JavaStdinAndStdoutI(TestInfo ti) {super(ti);}	/*
* overloaded log:

	private static void log(String msg) {}
/* */

	Scanner scan;

	public void execute() {
		log("execute");
		scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
	}

// hr:
	public static void main(String[] args) {new Solution().execute();}
}
