package io.github.amarcinkowski.hackerrank;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.utils.FileUtils;

public class SolutionBuilder {

	private static final String PACKAGE_TWIG = "PACKAGE";

	private static final String CLASSNAME_TWIG = "CLASSNAME";

	private static final String SOLUTION_TWIG_TEMPLATE = "src/main/resources/solution.twig";
	private static final String TEST_TWIG_TEMPLATE = "src/main/resources/test.twig";

	private static final Logger logger = LoggerFactory.getLogger(SolutionBuilder.class);

	public final static String SRC_DIR = "src/main/java";

	private String className;
	private String packageName;
	private String domain;
	private boolean createFile = false;
	private boolean fromTemplate = false;
	private boolean withInOutFiles = false;
	private boolean addJUnit = false;

	private String description;
	private String group;

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
		return String.format("%s.%s", packageName, className);
	}

	private static File javaFileFromCanonical(String name) {
		return new File(String.format("%s/%s.%s", SRC_DIR, name.replace(".", "/"), "java"));
	}

	private void create(File classFile) throws IOException {
		if (createFile) {
			FileUtils.createFileIfNotExisting(classFile);
		}
	}

	private void copyTemplate(File classFile) throws IOException {
		if (fromTemplate) {
			JtwigTemplate template = JtwigTemplate.fileTemplate(new File(SOLUTION_TWIG_TEMPLATE));
			JtwigModel model = JtwigModel.newModel().with(CLASSNAME_TWIG, className).with(PACKAGE_TWIG, packageName);
			template.render(model, new FileOutputStream(classFile));
		}
	}

	private void addJUnitTest() throws IOException {
		if (addJUnit) {
			JtwigTemplate template = JtwigTemplate.fileTemplate(new File(TEST_TWIG_TEMPLATE));
			JtwigModel model = JtwigModel.newModel().with("DOMAIN", domain).with("GROUP", group)
					.with("DESCRIPTION", description).with("CLASS", className)
					.with("METHOD", decapitalizeFirstLetter(className));
			String filepath = "/home/amarcinkowski/git/hackerrank/src/test/java/io/gihtub/amarcinkowski/hackerrank/java/tests/Advanced.java";
			String truncated = truncateFile(filepath);
			String methodString = template.render(model);
			StringBuilder sb = new StringBuilder();
			sb.append(truncated);
			sb.append(methodString);
			Files.write(Paths.get(filepath), sb.toString().getBytes());
		}
	}

	// TODO replace with StringUtils.uncaitalize
	private String decapitalizeFirstLetter(String string) {
		char c[] = string.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		return new String(c);
	}

	private static String truncateFile(String filepath) throws IOException {
		String classEnding = "\\s*+}\\s*+\n";
		try (Stream<String> lines = Files.lines(Paths.get(filepath))) {
			List<String> replaced = lines.collect(Collectors.toList());
			int i = replaced.size();
			do {
				logger.trace(replaced.get(--i));
			} while (replaced.get(i).matches(classEnding));
			return replaced.stream().limit(i).collect(Collectors.joining("\n"));
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
		addJUnitTest();
		return new Solution(getCanonical());
	}

	public SolutionBuilder domain(String domain) {
		this.domain = domain;
		return this;
	}

	public SolutionBuilder description(String desc) {
		this.description = desc;
		return this;
	}

	public SolutionBuilder group(String group) {
		this.group = group;
		this.packageName = String.format("io.github.amarcinkowski.hackerrank.%s", group);
		return this;
	}

}
