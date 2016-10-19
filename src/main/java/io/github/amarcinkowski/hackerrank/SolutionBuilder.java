package io.github.amarcinkowski.hackerrank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import io.github.amarcinkowski.FileUtils;

public class SolutionBuilder {

	private final static Logger logger = Logger.getLogger(SolutionBuilder.class);

	public final static String SRC_DIR = "src";

	public final static String SOLUTION_TEMPLATE = "package PACKAGE;\n\n" + "import java.util.Scanner;\n"
			+ "import com.hackerrank.java.Solution;\n\npublic class CLASSNAME extends Solution {\n"
			+ "public CLASSNAME(String name) {super(name);}"
			+ "	/*\n* overloaded log:\n\n\tprivate void log(String msg) {}\n/* */\n\n"
			+ "\tScanner scanner;\n\n\tpublic void execute() "
			+ "{\n\t\tlog(\"execute\");\n\t\tscanner = new Scanner(System.in);"
			+ "\n\t\tint x = scanner.nextInt();\n\t}\n\n// hr:\n//	public static void main(String[] args) "
			+ "{new Solution().execute();}\n}\n";

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
			FileWriter fw = new FileWriter(classFile);
			fw.write(SOLUTION_TEMPLATE.replace("CLASSNAME", className).replaceAll("PACKAGE", packageName));
			fw.close();
		}
	}

	public Solution build() throws IOException {
		logger.info("building new solution");
		File classFile = javaFileFromCanonical(getCanonical());
		create(classFile);
		copyTemplate(classFile);
		return new Solution(getCanonical());
	}

	public static void moveSolution(String src, String dest) {
		try {
			Solution srcSolution = new SolutionBuilder().fromCanonical(src).build();
			Solution destSolution = new SolutionBuilder().fromCanonical(dest).build();
			srcSolution.getExpectedFile().renameTo(destSolution.getExpectedFile());
			srcSolution.getInFile().renameTo(destSolution.getInFile());
			srcSolution.getResultFile().renameTo(destSolution.getResultFile());
			javaFileFromCanonical(src).renameTo(javaFileFromCanonical(dest));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
