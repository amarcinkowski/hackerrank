package io.github.amarcinkowski.solutionframework;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.utils.FileUtils;
import io.github.amarcinkowski.utils.TemplateUtils;

public class SolutionBuilder {

	private final static Logger logger = LoggerFactory.getLogger(SolutionBuilder.class);

	private static final String CLASS_ENDING_PATTERN = "\\s*+}\\s*+\n";
	private static final String CLASS_END = "\n}\n";

	private String platform;
	private String classname;
	private String domain;
	private String subdomain;
	private String description;

	public static Stream<String> fileToStream(String filepath) throws IOException {
		return Files.lines(Paths.get(filepath));
	}

	private void appendMethodToTestSuite(Solution solution) throws IOException {
		File file = solution.suite();
		String truncated = FileUtils.truncateFile(file.getAbsolutePath(), CLASS_ENDING_PATTERN);
		String method = TemplateUtils.getRenderedTemplate(solution.getPlatform(), solution.getDomain(),
				solution.getSubdomain(), solution.getClassName(), solution.getDescription());
		String contents = new StringBuilder().append(truncated).append(method).append(CLASS_END).toString();
		Files.write(Paths.get(file.getAbsolutePath()), contents.getBytes());
	}

	private void createFiles(Solution solution) throws IOException {
		FileUtils.createFileIfNotExisting(solution.java());
		String template = TemplateUtils.getRenderedTemplate(solution.getClassName(), solution.getPackage());
		Files.write(solution.java().toPath(), template.getBytes());
		FileUtils.createFileIfNotExisting(solution.expected());
		FileUtils.createFileIfNotExisting(solution.in());
		FileUtils.createFileIfNotExisting(solution.suite());
		appendMethodToTestSuite(solution);
	}

	public Solution build() throws IOException {
		logger.trace("Build new Solution and create files");
		Solution solution = new Solution();
		solution.setPlatform(platform);
		solution.setClassName(classname);
		solution.setDomain(domain);
		solution.setSubdomain(subdomain);
		solution.setDescription(description);
		createFiles(solution);
		return solution;
	}

	public SolutionBuilder className(String className) {
		this.classname = className;
		return this;
	}

	public SolutionBuilder platform(String platform) {
		this.platform = platform;
		return this;
	}

	public SolutionBuilder description(String description) {
		this.description = description;
		return this;
	}

	public SolutionBuilder domain(String domain) {
		this.domain = domain;
		return this;
	}

	public SolutionBuilder subdomain(String subdomain) {
		this.subdomain = subdomain;
		return this;
	}

}
