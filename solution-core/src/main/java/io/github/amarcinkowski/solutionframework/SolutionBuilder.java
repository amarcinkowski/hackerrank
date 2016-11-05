package io.github.amarcinkowski.solutionframework;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import io.github.amarcinkowski.utils.FileUtils;
import io.github.amarcinkowski.utils.TemplateUtils;

public class SolutionBuilder {

//	private static final String CANONICAL_FORMAT_STRING = "%s.%s";

	private static final String CLASS_ENDING_PATTERN = "\\s*+}\\s*+\n";
	private static final String CLASS_END = "\n}\n";

	private String platform;
	// TODO rename to classname
	private String className;
	private String domain;
	private String subdomain;
	private String description;
//	private String packageName;
//	private String groupName;
//	private boolean createFile = false;
//	private boolean fromTemplate = false;
//	private boolean withInOutFiles = false;
//	private boolean addJUnit = false;

	public static Stream<String> fileToStream(String filepath) throws IOException {
		return Files.lines(Paths.get(filepath));
	}

	private void appendMethodToTestSuite(String group) throws IOException {
		File file = new File("/var/spool/anacron/cron.daily"); // SolutionUtils.getJUnitFile(group);
		String truncated = FileUtils.truncateFile(file.getAbsolutePath(), CLASS_ENDING_PATTERN);
		String method = TemplateUtils.getRenderedTemplate(domain, subdomain, className, description);
		String contents = new StringBuilder().append(truncated).append(method).append(CLASS_END).toString();
		Files.write(Paths.get(file.getAbsolutePath()), contents.getBytes());
	}


	public Solution build() throws IOException {
//		File classFile = SolutionUtils.getJavaFile(canonical);
		Solution solution = new Solution();
		solution.setPlatform(platform);
		solution.setClassName(className);
		solution.setDomain(domain);
		solution.setSubdomain(subdomain);
		solution.setDescription(description);
		FileUtils.createFileIfNotExisting(solution.java());
//		create(classFile);
		//copyTemplate(classFile);
		//createInOutFiles();
		//addJUnitTest(subdomainName);
		return solution;
	}

	public SolutionBuilder className(String className) {
		this.className = className;
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
	

//	private void addJUnitTest(String group) throws IOException {
//		if (addJUnit) {
//			appendMethodToTestSuite(group);
//		}
//	}
	
//	public SolutionBuilder fromCanonical(String canonical) {
//		className = canonical.substring(canonical.lastIndexOf(".") + 1);
//		packageName = canonical.substring(0, canonical.lastIndexOf("."));
//		return this;
//	}

//	public SolutionBuilder fromTemplate(boolean template) {
//		this.fromTemplate = template;
//		return this;
//	}

//	public String getCanonical() {
//		this.packageName = String.format(PACKAGE_FORMAT_STRING, platform, groupName);
//		return String.format(CANONICAL_FORMAT_STRING, packageName, className);
//	}

//	private void copyTemplate(File classFile) throws IOException {
//	if (fromTemplate) {
//		String template = TemplateUtils.getRenderedTemplate(className, packageName);
//		Files.write(classFile.toPath(), template.getBytes());
//	}
//}

//private void create(File classFile) throws IOException {
//	if (createFile) {
//		FileUtils.createFileIfNotExisting(classFile);
//	}
//}

//public SolutionBuilder createFile(boolean create) {
//	this.createFile = create;
//	return this;
//}

//private void createInOutFiles() throws IOException {
	/*if (withInOutFiles) {
		FileUtils.createFileIfNotExisting(FileUtils.getInResourceFile(getCanonical()));
		FileUtils.createFileIfNotExisting(FileUtils.getExpectedResourceFile(getCanonical()));
	}*/
//}

	/*public SolutionBuilder packageName(String packageName) {
		this.packageName = packageName;
		return this;
	}*/

//	public SolutionBuilder withInOutFiles(boolean withInOut) {
//		this.withInOutFiles = withInOut;
//		return this;
//	}

//	public SolutionBuilder withJUnit(boolean addJunit) {
//		this.addJUnit = addJunit;
//		return this;
//	}

//	public SolutionBuilder createAll() {
//		return this.createFile(true).fromTemplate(true).withInOutFiles(true).withJUnit(true);
//	}

}
