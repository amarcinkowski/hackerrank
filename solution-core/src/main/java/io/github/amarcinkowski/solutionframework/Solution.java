package io.github.amarcinkowski.solutionframework;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.solutionframework.exception.NotImplementedException;
import io.github.amarcinkowski.utils.StringUtils;

public class Solution implements Command {

	private final static Logger logger = LoggerFactory.getLogger(Solution.class);

	public final static String PACKAGE = "io.github.amarcinkowski";
	private static final String SOLUTION_FILE = "%s-solutions/src/main/java/io/github/amarcinkowski/%s/%s/%s.java";
	private static final String IO_DATA_FILE = "%s-solutions/src/test/resources/%s/%s.%s";

	// private final String name;
	private String platform;
	private String className;
	private String domain;
	private String subdomain;
	private String description;

	public Solution(TestInfo ti) {
		// System.out.println("Solution(name) Constructor");
		// this.className = name;
		this.platform = ti.platform();
		this.className = ti.solutionClass();
		this.domain = ti.domain();
		this.subdomain = ti.group();
		this.description = ti.taskDescription();
	}

	// public String getName() {
	// return name;
	// }

	public Solution() {
		throw new NotImplementedException();
	}

	protected static void log(Object log) {
		if (System.getenv("LOGNAME").equals("amarcinkowski")) {
			logger.debug(log.toString());
		}
	}

	public void execute() {
		throw new NotImplementedException();
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}

	public String getClassName() {
		return className;
	}

	public File java() {
		String path = String.format(SOLUTION_FILE, platform, platform, StringUtils.packagify(subdomain), className);
		return new File(path);
	}

	public File in() {
		String path = String.format(IO_DATA_FILE, platform, StringUtils.packagify(subdomain), className, ".in");
		return new File(path);
	}

	public File out() {
		String path = String.format(IO_DATA_FILE, platform, StringUtils.packagify(subdomain), className, ".out");
		return new File(path);
	}

	public File expected() {
		String path = String.format(IO_DATA_FILE, platform, StringUtils.packagify(subdomain), className, ".expected");
		return new File(path);
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s", className, platform, domain, subdomain, description);
	}

}
