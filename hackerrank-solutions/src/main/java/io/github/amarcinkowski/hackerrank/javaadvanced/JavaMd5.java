package io.github.amarcinkowski.hackerrank.javaadvanced;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

/*
 * imports
 */
import java.security.MessageDigest;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;


public class JavaMd5 extends Solution {
public JavaMd5(TestInfo ti) {super(ti);}	/*
* overloaded log:

	private static void log(String msg) {}
/* */

	Scanner scanner;

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		String s = scanner.next();
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			String hex = (new HexBinaryAdapter()).marshal(digest.digest(s.getBytes())).toLowerCase();
			log(hex);
			System.out.println(hex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// hr:
	public static void main(String[] args) {new Solution().execute();}
}
