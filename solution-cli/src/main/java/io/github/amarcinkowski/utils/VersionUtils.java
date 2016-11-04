package io.github.amarcinkowski.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.github.amarcinkowski.solutionframework.Cli;

public class VersionUtils {

	public static void printVersion() throws IOException {
		Properties properties = new Properties();
		InputStream is = new Cli().getClass().getResourceAsStream("/version.properties");
		properties.load(is);
		System.out.println(properties.getProperty("version"));
	}

}
