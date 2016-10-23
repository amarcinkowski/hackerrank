package io.github.amarcinkowski.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils {

	public static final Logger logger = LoggerFactory.getLogger(IOUtils.class);

	public static final void redirectInput(File in) throws FileNotFoundException {
		logger.trace("Redirect stdin");
		FileInputStream is = null;
		is = new FileInputStream(in);
		System.setIn(is);
	}

	public static final void redirectOutput(File out) throws FileNotFoundException {
		logger.trace("Redirect stdout");
		FileOutputStream fos = null;
		fos = new FileOutputStream(out);
		System.setOut(new PrintStream(new BufferedOutputStream(fos)));
	}

	public static void resetRedirectedIO() {
		logger.trace("Reset redirected stdout");
		System.out.flush();
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}

	public final static void redirectIO(File in, File out) throws Exception {
		redirectInput(in);
		redirectOutput(out);
		logger.trace("IO redirected");
	}

}
