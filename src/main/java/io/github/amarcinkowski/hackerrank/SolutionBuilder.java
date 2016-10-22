package io.github.amarcinkowski.hackerrank;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.utils.FileUtils;
import io.github.amarcinkowski.utils.TemplateUtils;

public class SolutionBuilder {

	private static final String NEW_LINE = "\n";

	private static final String FILEPATH_FORMAT_STRING = "%s/%s.%s";
	private static final String CANONICAL_FORMAT_STRING = "%s.%s";
	private static final String PACKAGE_FORMAT_STRING = "io.github.amarcinkowski.hackerrank.%s";
	private static final String CLASS_ENDING_PATTERN = "\\s*+}\\s*+\n";

	private static final Logger logger = LoggerFactory.getLogger(SolutionBuilder.class);

	public final static String SRC_DIR = "src/main/java";

	private String className;
	private String packageName;
	private String domainName;
	private boolean createFile = false;
	private boolean fromTemplate = false;
	private boolean withInOutFiles = false;
	private boolean addJUnit = false;

	private String hackerrankDescription;
	private String groupName;

	public SolutionBuilder className(String className) {
		this.className = className;
		return this;
	}

	public SolutionBuilder packageName(String packageName) {
		this.packageName = packageName;
		return this;
	}

	public SolutionBuilder fromCanonical(String canonical) {
		className = canonical.substring(canonical.lastIndexOf(".") + 1);
		packageName = canonical.substring(0, canonical.lastIndexOf("."));
		return this;
	}

	public SolutionBuilder createFile(boolean create) {
		this.createFile = create;
		return this;
	}

	public SolutionBuilder fromTemplate(boolean template) {
		this.fromTemplate = template;
		return this;
	}

	public SolutionBuilder withJUnit(boolean addJunit) {
		this.addJUnit = addJunit;
		return this;
	}

	public String getCanonical() {
		return String.format(CANONICAL_FORMAT_STRING, packageName, className);
	}

	private static File javaFileFromCanonical(String name) {
		String dir = name.replace(".", "/");
		String path = String.format(FILEPATH_FORMAT_STRING, SRC_DIR, dir, "java");
		return new File(path);
	}

	private void create(File classFile) throws IOException {
		if (createFile) {
			FileUtils.createFileIfNotExisting(classFile);
		}
	}

	private void copyTemplate(File classFile) throws IOException {
		if (fromTemplate) {
			String template = TemplateUtils.getRenderedTemplate(className, packageName);
			Files.write(classFile.toPath(), template.getBytes());
		}
	}
	
	private void addJUnitTest(File file) throws IOException {
		if (addJUnit) {
			String truncated = truncateFile(file.getAbsolutePath());
			String methodString = TemplateUtils.getRenderedTemplate(domainName, groupName, className, hackerrankDescription);
			StringBuilder sb = new StringBuilder();
			sb.append(truncated);
			sb.append(methodString);
			sb.append("\n}\n");
			Files.write(Paths.get(file.getAbsolutePath()), sb.toString().getBytes());
		}
	}

	private static Stream<String> fileToStream(String filepath) throws IOException {
		return Files.lines(Paths.get(filepath));
	}

	private static String truncateFile(String filepath) throws IOException {
		try (Stream<String> lines = fileToStream(filepath)) {
			List<String> replaced = lines.collect(Collectors.toList());
			int i = replaced.size();
			do {
				logger.trace(replaced.get(--i));
			} while (replaced.get(i).matches(CLASS_ENDING_PATTERN));
			return replaced.stream().limit(i).collect(Collectors.joining(NEW_LINE));
		}
	}

	public SolutionBuilder withInOutFiles(boolean withInOut) {
		this.withInOutFiles = withInOut;
		return this;
	}

	private void createInOutFiles() throws IOException {
		if (withInOutFiles) {
			FileUtils.createFileIfNotExisting(FileUtils.getInFile(getCanonical()));
			FileUtils.createFileIfNotExisting(FileUtils.getExpectedFile(getCanonical()));
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

	public SolutionBuilder domain(String domain) {
		this.domainName = domain;
		return this;
	}

	public SolutionBuilder description(String desc) {
		this.hackerrankDescription = desc;
		return this;
	}

	public SolutionBuilder group(String group) {
		this.groupName = group;
		this.packageName = String.format(PACKAGE_FORMAT_STRING, group);
		return this;
	}

}
