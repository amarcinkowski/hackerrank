package io.github.amarcinkowski.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.github.amarcinkowski.solutionframework.Cli;

public class PopertiesUtils {

	private static final String VERSION = "version";
	private static final String VERSION_PROPERTIES = "/version.properties";
	private static final Properties properties = new Properties();

	private static InputStream getResource() {
		return new Cli().getClass().getResourceAsStream(VERSION_PROPERTIES);
	}

	private static Properties getProps() throws IOException {
		properties.clear();
		properties.load(getResource());
		return properties;
	}

	public static void printVersion() throws IOException {
		System.out.println(getProps().getProperty(VERSION));
	}

}
