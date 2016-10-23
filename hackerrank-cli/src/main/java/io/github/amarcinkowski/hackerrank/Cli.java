package io.github.amarcinkowski.hackerrank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Level;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.hackerrank.browser.ChromeExecutor;
import io.github.amarcinkowski.hackerrank.browser.HackerrankPageReader;
import io.github.amarcinkowski.hackerrank.browser.RobotHelper;
import io.github.amarcinkowski.utils.LoggingUtils;
import io.github.amarcinkowski.utils.StringUtils;

public class Cli {

	public static final String JSON_PATTERN = "HR.PREFETCH_DATA =(.*);[ \n]+HR.MANIFEST_HASH";
	private static final String UNSOLVED_CHALLENGE_PATTERN = "([A-Za-z0-9\\- ]+)\nSuccess Rate: .{2,7} Max Score: [0-9]+ Difficulty: [A-Za-z]+ Solve Challenge\n";
	final Logger logger = LoggerFactory.getLogger(Cli.class);

	public static List<String> findAllMultiline(String pattern, String content) {
		List<String> list = new ArrayList<>();
		Matcher m = Pattern.compile(pattern, Pattern.DOTALL).matcher(content);
		while (m.find()) {
			list.add(m.group(1));
		}
		return list;
	}

	public static void main(String[] args) throws IOException, MavenInvocationException, XPathExpressionException {

		Options options = createOptions();

		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("jt", options);

		// create the parser
		CommandLineParser parser = new DefaultParser();
		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("quiet")) {
				LoggingUtils.setLevel(Level.OFF);
			}

			if (line.hasOption("debug")) {
				LoggingUtils.setLevel(Level.TRACE);
			}

			if (line.hasOption("run")) {
				try {
					mavenInvoke();
				} catch (Exception e) {
					System.out.println("try running command: mvn test");
				}
			}

			if (line.hasOption("unsolved")) {
				String[] values = line.getOptionValues("u");
				String domain = values[0];
				String subdomain = values[1];
				System.out.println(unsolved(domain, subdomain));
			}

			if (line.hasOption("generate-all")) {
				String[] values = line.getOptionValues("g");
				String domain = values[0];
				String subdomain = values[1];
				for (String s : unsolved(domain, subdomain)) {
					create(StringUtils.normalize(s), domain, subdomain.replace("java-", ""), domain + subdomain + s);
				}
			}

			if (line.hasOption("list")) {
				HackerrankPageReader.list();
			}

			if (line.hasOption("create")) {
				String[] values = line.getOptionValues("c");
				String domain = values[0];
				String subdomain = values[1];
				String className = values[2];
				String desc = values[3];
				create(className, domain, subdomain, desc);
			}

		} catch (ParseException exp) {
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
		}

	}
	
	private static void create(String className, String domain, String subdomain, String desc) throws IOException {
//		SolutionBuilder builder = new SolutionBuilder();
//		builder.className(className).subdomain(subdomain).domain(domain).description(desc).createAll().build();
		// TODO build solution
	}

	private static List<String> unsolved(String domain, String subdomain) {
		ChromeExecutor.openBrowser(domain, subdomain);
		String pageContent = RobotHelper.getPageContent();
		String p = UNSOLVED_CHALLENGE_PATTERN;
		return findAllMultiline(p, pageContent);
	}

	private static void mavenInvoke() throws MavenInvocationException {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File("pom.xml"));
		request.setGoals(Collections.singletonList("test"));

		Invoker invoker = new DefaultInvoker();
		System.setProperty("maven.home", "/usr/share/maven");
		InvocationResult ir = invoker.execute(request);
		if (ir.getExitCode() != 0) {
			System.err.println("mvn failed");
		}
	}

	private static Options createOptions() {
		Option help = new Option("h", "help", false, "print this message");
		Option version = new Option("v", "version", false, "print the version information and exit");
		Option quiet = new Option("q", "quiet", false, "be extra quiet");
		Option verbose = new Option("d", "debug", false, "be extra verbose");

		Option run = Option.builder("r").longOpt("run").desc("run mvn test").build();

		Option create = Option.builder("c").longOpt("create").numberOfArgs(3)
				.argName("domain,subdomain,class,description").valueSeparator(',').hasArgs()
				.desc("create solution from template").build();
		Option list = Option.builder("l").longOpt("list").desc("list available domains |- subdomains (slug)").build();
		Option uns = Option.builder("u").longOpt("unsolved").numberOfArgs(2).argName("domain,subdomain")
				.valueSeparator(',').hasArgs().desc("list yet unsolved challenges").build();

		Option gen = Option.builder("g").longOpt("generate-all").numberOfArgs(2).argName("domain,subdomain")
				.valueSeparator(',').hasArgs().desc("generate template for all unsolved challenges").build();

		Options options = new Options();
		options.addOption(help);
		options.addOption(version);
		options.addOption(quiet);
		options.addOption(verbose);
		options.addOption(run);
		options.addOption(create);
		options.addOption(list);
		options.addOption(uns);
		options.addOption(gen);
		return options;
	}

}
