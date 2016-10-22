package io.github.amarcinkowski.hackerrank;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import io.github.amarcinkowski.utils.FileUtils;
import io.github.amarcinkowski.utils.SolutionUtils;
import io.github.amarcinkowski.utils.TemplateUtils;

public class SolutionBuilder {

	private static final String CANONICAL_FORMAT_STRING = "%s.%s";
	private static final String PACKAGE_FORMAT_STRING = "io.github.amarcinkowski.hackerrank.%s";
	private static final String CLASS_ENDING_PATTERN = "\\s*+}\\s*+\n";
	private static final String CLASS_END = "\n}\n";

	private String className;
	private String packageName;
	private String domainName;
	private String hackerrankDescription;
	private String groupName;
	private boolean createFile = false;
	private boolean fromTemplate = false;
	private boolean withInOutFiles = false;
	private boolean addJUnit = false;

	public static Stream<String> fileToStream(String filepath) throws IOException {
		return Files.lines(Paths.get(filepath));
	}

	private void appendMethodToTestSuite(String group) throws IOException {
		File file = SolutionUtils.getJUnitFile(group);
		String truncated = FileUtils.truncateFile(file.getAbsolutePath(), CLASS_ENDING_PATTERN);
		String method = TemplateUtils.getRenderedTemplate(domainName, groupName, className, hackerrankDescription);
		String contents = new StringBuilder().append(truncated).append(method).append(CLASS_END).toString();
		Files.write(Paths.get(file.getAbsolutePath()), contents.getBytes());
	}

	private void addJUnitTest(String group) throws IOException {
		if (addJUnit) {
			appendMethodToTestSuite(group);
		}
	}

	public Solution build() throws IOException {
		File classFile = SolutionUtils.getJavaFile(getCanonical());
		create(classFile);
		copyTemplate(classFile);
		createInOutFiles();
		addJUnitTest(groupName);
		return new Solution(getCanonical());
	}

	public SolutionBuilder className(String className) {
		this.className = className;
		return this;
	}

	private void copyTemplate(File classFile) throws IOException {
		if (fromTemplate) {
			String template = TemplateUtils.getRenderedTemplate(className, packageName);
			Files.write(classFile.toPath(), template.getBytes());
		}
	}

	private void create(File classFile) throws IOException {
		if (createFile) {
			FileUtils.createFileIfNotExisting(classFile);
		}
	}

	public SolutionBuilder createFile(boolean create) {
		this.createFile = create;
		return this;
	}

	private void createInOutFiles() throws IOException {
		if (withInOutFiles) {
			FileUtils.createFileIfNotExisting(FileUtils.getInResourceFile(getCanonical()));
			FileUtils.createFileIfNotExisting(FileUtils.getExpectedResourceFile(getCanonical()));
		}
	}

	public SolutionBuilder description(String desc) {
		this.hackerrankDescription = desc;
		return this;
	}

	public SolutionBuilder domain(String domain) {
		this.domainName = domain;
		return this;
	}

	public SolutionBuilder fromCanonical(String canonical) {
		className = canonical.substring(canonical.lastIndexOf(".") + 1);
		packageName = canonical.substring(0, canonical.lastIndexOf("."));
		return this;
	}

	public SolutionBuilder fromTemplate(boolean template) {
		this.fromTemplate = template;
		return this;
	}

	public String getCanonical() {
		return String.format(CANONICAL_FORMAT_STRING, packageName, className);
	}

	public SolutionBuilder group(String group) {
		this.groupName = group;
		this.packageName = String.format(PACKAGE_FORMAT_STRING, group);
		return this;
	}

	public SolutionBuilder packageName(String packageName) {
		this.packageName = packageName;
		return this;
	}

	public SolutionBuilder withInOutFiles(boolean withInOut) {
		this.withInOutFiles = withInOut;
		return this;
	}

	public SolutionBuilder withJUnit(boolean addJunit) {
		this.addJUnit = addJunit;
		return this;
	}

}
