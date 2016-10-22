package io.github.amarcinkowski.hackerrank;

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

	private static final Logger logger = LoggerFactory.getLogger(SolutionBuilder.class);

	private static final String FILEPATH_FORMAT_STRING = "%s/%s.%s";
	private static final String CANONICAL_FORMAT_STRING = "%s.%s";
	private static final String PACKAGE_FORMAT_STRING = "io.github.amarcinkowski.hackerrank.%s";
	private static final String CLASS_ENDING_PATTERN = "\\s*+}\\s*+\n";
	private static final String SRC_DIR = "src/main/java";
	private static final String CLASS_END = "\n}\n";
	private static final String SLASH = "/";
	private static final String DOT = ".";
	private static final String JAVA_EXT = "java";

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

	private static File javaFileFromCanonical(String name) {
		String dir = name.replace(DOT, SLASH);
		String path = String.format(FILEPATH_FORMAT_STRING, SRC_DIR, dir, JAVA_EXT);
		return new File(path);
	}

	private void addJUnitTest(File file) throws IOException {
		if (addJUnit) {
			String truncated = FileUtils.truncateFile(file.getAbsolutePath(), CLASS_ENDING_PATTERN);
			String method = TemplateUtils.getRenderedTemplate(domainName, groupName, className, hackerrankDescription);
			String contents = new StringBuilder().append(truncated).append(method).append(CLASS_END).toString();
			Files.write(Paths.get(file.getAbsolutePath()), contents.getBytes());
		}
	}

	public Solution build() throws IOException {
		logger.debug("building new solution");
		File classFile = javaFileFromCanonical(getCanonical());
		create(classFile);
		copyTemplate(classFile);
		createInOutFiles();
		addJUnitTest(classFile);
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
			FileUtils.createFileIfNotExisting(FileUtils.getInFile(getCanonical()));
			FileUtils.createFileIfNotExisting(FileUtils.getExpectedFile(getCanonical()));
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
		className = canonical.substring(canonical.lastIndexOf(DOT) + 1);
		packageName = canonical.substring(0, canonical.lastIndexOf(DOT));
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
