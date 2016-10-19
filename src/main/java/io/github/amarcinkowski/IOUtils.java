package io.github.amarcinkowski;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;

public class IOUtils {

	private final static Logger log = Logger.getLogger(IOUtils.class);

	public static final void redirectInput(File in) throws FileNotFoundException {
		log.debug("Redirect stdin");
		FileInputStream is = null;
		is = new FileInputStream(in);
		System.setIn(is);
	}

	public static final void redirectOutput(File out) throws FileNotFoundException {
		log.debug("Redirect stdout");
		FileOutputStream fos = null;
		fos = new FileOutputStream(out);
		System.setOut(new PrintStream(new BufferedOutputStream(fos)));
	}

	public static void resetRedirectedIO() {
		log.debug("Reset redirected stdout");
		System.out.flush();
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}

	public final static void redirectIO(File in, File out) throws Exception {
		redirectInput(in);
		redirectOutput(out);
		log.debug("IO redirected");
	}

}
