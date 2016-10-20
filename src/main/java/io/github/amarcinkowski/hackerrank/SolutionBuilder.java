package io.github.amarcinkowski.hackerrank;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.utils.FileUtils;

public class SolutionBuilder {

	private static final String PACKAGE_TWIG = "PACKAGE";

	private static final String CLASSNAME_TWIG = "CLASSNAME";

	private static final String SOLUTION_TWIG_TEMPLATE = "src/main/resources/solution.twig";

	private static final Logger logger = LoggerFactory.getLogger(SolutionBuilder.class);

	public final static String SRC_DIR = "src/main/java";

	private String className;
	private String packageName;
	private boolean createFile = false;
	private boolean fromTemplate = false;

	public SolutionBuilder setClassName(String className) {
		this.className = className;
		return this;
	}

	public SolutionBuilder setPackageName(String packageName) {
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

	public Solution build() throws IOException {
		logger.debug("building new solution");
		File classFile = javaFileFromCanonical(getCanonical());
		create(classFile);
		copyTemplate(classFile);
		FileUtils.createFileIfNotExisting(FileUtils.getInFile(getCanonical()));
		FileUtils.createFileIfNotExisting(FileUtils.getExpectedFile(getCanonical()));
		return new Solution(getCanonical());
	}

}
