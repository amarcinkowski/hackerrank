package io.github.amarcinkowski.hackerrank;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import io.github.amarcinkowski.utils.LoggingUtils;

public class Cli {

	final Logger logger = LoggerFactory.getLogger(Cli.class);

	public static void main(String[] args) throws IOException, MavenInvocationException {

		Options options = createOptions();

		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("jt", options);

		// create the parser
		CommandLineParser parser = new DefaultParser();
		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);

			LoggingUtils.setLevel(Level.INFO);

			if (line.hasOption("quiet")) {
				LoggingUtils.setLevel(Level.OFF);
			}

			if (line.hasOption("debug")) {
				LoggingUtils.setLevel(Level.TRACE);
			}

			if (line.hasOption("run")) {
				try {
					InvocationRequest request = new DefaultInvocationRequest();
					request.setPomFile(new File("pom.xml"));
					request.setGoals(Collections.singletonList("test"));

					Invoker invoker = new DefaultInvoker();
					System.setProperty("maven.home", "/usr/share/maven");
					InvocationResult ir = invoker.execute(request);
					if (ir.getExitCode() != 0) {
						throw new Exception("mvn failed");
					}
				} catch (Exception e) {
					System.out.println("try running command: mvn test");
				}
			}

			if (line.hasOption("create")) {
				String[] values = line.getOptionValues("c");
				String domain = values[0];
				String group = values[1];
				String solutionClass = values[2];
				String desc = values[3];
				String canonical = String.format("io.github.amarcinkowski.hackerrank.%s.%s", group, solutionClass);
				new SolutionBuilder().fromCanonical(canonical).createFile(true).fromTemplate(true).build().getClass();
				System.out.println(canonical + "created");
				// ChromeExecutor.saveAndCloseAll(new JobType(url, taskName),
				// startIndex, endIndex);
			}

		} catch (ParseException exp) {
			// oops, something went wrong
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
		}

	}

	private static Options createOptions() {
		Option help = new Option("h", "help", false, "print this message");
		Option version = new Option("v", "version", false, "print the version information and exit");
		Option quiet = new Option("q", "quiet", false, "be extra quiet");
		Option verbose = new Option("d", "debug", false, "be extra verbose");

		Option xxx = Option.builder("r").longOpt("run").desc("run mvn test").build();

		Option create = Option.builder("c").longOpt("create").numberOfArgs(3).argName("domain,group,class,description")
				.valueSeparator(',').hasArgs().desc("create solution from template").build();

		// Option save =
		// Option.builder("s").longOpt("save").numberOfArgs(4).argName("url,task,start,end")
		// .valueSeparator(',').hasArgs().desc("save tabs in chrome to txt
		// files").build();

		Options options = new Options();
		options.addOption(help);
		options.addOption(version);
		options.addOption(quiet);
		options.addOption(verbose);
		options.addOption(xxx);
		options.addOption(create);
		// options.addOption(save);
		return options;
	}

}
