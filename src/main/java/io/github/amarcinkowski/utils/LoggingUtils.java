package io.github.amarcinkowski.utils;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

import io.github.amarcinkowski.hackerrank.Solution;

public class LoggingUtils {

	public static void setLevel(Level level) {
		System.out.println("Setting level to " + level);
		LogManager.getLogger(Solution.class).setLevel(level);
	}

}
