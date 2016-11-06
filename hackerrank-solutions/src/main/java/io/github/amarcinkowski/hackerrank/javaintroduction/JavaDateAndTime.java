package io.github.amarcinkowski.hackerrank.javaintroduction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class JavaDateAndTime extends Solution {
	public JavaDateAndTime(TestInfo ti) {
		super(ti);
	} /*
		 * overloaded log:
		  
		 private static void log(String msg) {} /*
		 */

	Scanner in;

	public void execute() {
		log("execute");
		in = new Scanner(System.in);
		String month = in.next();
		String day = in.next();
		String year = in.next();
		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1,
				Integer.parseInt(day));
		Date d = calendar.getTime();
		log(new SimpleDateFormat("MM dd yyyy").format(d));
		int i = calendar.get(Calendar.DAY_OF_WEEK);
		log("" + i);
		switch (i) {
		case 2:
			System.out.println("MONDAY");
			break;
		case 3:
			System.out.println("TUESDAY");
			break;
		case 4:
			System.out.println("WEDNESDAY");
			break;
		case 5:
			System.out.println("THURSDAY");
			break;
		case 6:
			System.out.println("FRIDAY");
			break;
		case 7:
			System.out.println("SATURDAY");
			break;
		case 1:
			System.out.println("SUNDAY");
			break;
		}
	}

	// hr:
	public static void main(String[] args) {
		new Solution().execute();
	}
}
