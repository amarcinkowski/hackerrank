package io.github.amarcinkowski.hackerrank.advanced;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.github.amarcinkowski.hackerrank.Solution;

public class CanYouAccess extends Solution {
	public CanYouAccess(String name) {
		super(name);
	} /*
		 * COPY FROM LINE BELOW:
		 * 
		 * private void log(String msg) {} /*
		 */

	public void execute() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int num = Integer.parseInt(br.readLine().trim());
			Object o;// Must be used to hold the reference of the instance of
						// the class Solution.Inner.Private

			o = new Inner().new Private();
			String s = ((Inner.Private) o).powerof2(num);
			System.out.printf("%d is %s\n", num, s);

			System.out.println("An instance of class: " + o.getClass().getCanonicalName()
					.replaceAll("com.hackerrank.java.advanced.CanYouAccess.", "Solution.") + " has been created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class Inner {
		private class Private {
			private String powerof2(int num) {
				return ((num & num - 1) == 0) ? "power of 2" : "not a power of 2";
			}
		}
	}// end of Inner

}// end of Solution
