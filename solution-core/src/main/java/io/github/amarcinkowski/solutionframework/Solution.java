package io.github.amarcinkowski.solutionframework;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.solutionframework.exception.NotImplementedException;
import io.github.amarcinkowski.utils.StringUtils;

public class Solution implements Command {

	private static final String PACKAGE = "io.github.amarcinkowski.%s.%s";

	private final static Logger logger = LoggerFactory.getLogger(Solution.class);

	private static final String SOLUTION_FILE = "%s/%s-solutions/src/main/java/io/github/amarcinkowski/%s/%s/%s.java";
	private static final String SUITE_FILE = "%s/%s-solutions/src/test/java/io/github/amarcinkowski/%s/%s/tests/%s.java";
	private static final String IO_DATA_FILE = "%s/%s-solutions/src/test/resources/%s/%s.%s";
	private static final String BASE_DIR = new File("..").getAbsolutePath();

	private String platform;
	private String classname;
	private String domain;
	private String subdomain;
	private String description;

	public Solution(TestInfo ti) {
		this.platform = ti.platform();
		this.classname = ti.solutionClass();
		this.domain = ti.domain();
		this.subdomain = ti.group();
		this.description = ti.taskDescription();
	}

	public Solution() {
	}

	protected static void log(Object log) {
		logger.debug(" | out> " + log.toString());
	}

	public void execute() {
		throw new NotImplementedException();
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setClassName(String className) {
		this.classname = className;
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
		return classname;
	}

	public String getPackage() {
		return String.format(PACKAGE, platform, StringUtils.packagify(subdomain));
	}

	public String getDomain() {
		return domain;
	}

	public String getSubdomain() {
		return subdomain;
	}

	public String getDescription() {
		return description;
	}

	public String getPlatform() {
		return platform;
	}

	public File java() {
		String subP = StringUtils.packagify(subdomain);
		String path = String.format(SOLUTION_FILE, BASE_DIR, platform, platform, subP, classname);
		return new File(path);
	}

	public File suite() {
		String domP = StringUtils.packagify(domain);
		String subC = StringUtils.camelify(subdomain);
		String path = String.format(SUITE_FILE, BASE_DIR, platform, platform, domP, subC);
		return new File(path);
	}

	public File in() {
		String subP = StringUtils.packagify(subdomain);
		String path = String.format(IO_DATA_FILE, BASE_DIR, platform, subP, classname, "in");
		return new File(path);
	}

	public File out() {
		String subP = StringUtils.packagify(subdomain);
		String path = String.format(IO_DATA_FILE, BASE_DIR, platform, subP, classname, "out");
		return new File(path);
	}

	public File expected() {
		String subP = StringUtils.packagify(subdomain);
		String path = String.format(IO_DATA_FILE, BASE_DIR, platform, subP, classname, "expected");
		return new File(path);
	}

}
