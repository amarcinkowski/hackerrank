package io.github.amarcinkowski.solutionframework;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.apache.maven.shared.invoker.MavenInvocationException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import io.github.amarcinkowski.utils.MavenUtils;

@Parameters(commandDescription = "Run all tests", separators = "=")
class TestCommand {
	@Parameter(names = { "-p", "--platform" }, description = "platform name e.g. hackerrank, codility", required = true)
	public String platform;
}

class CreateCommand extends TestCommand {
	@Parameter(names = { "-d", "--domain" }, required = true)
	public String domain;
	@Parameter(names = { "-s", "--subdomain" }, required = true)
	public String subdomain;
	@Parameter(names = { "-c", "--classname" }, required = true)
	public String classname;
	@Parameter(names = { "-t", "--description" })
	public String description;
}

public class Cli {

	private static final String CREATE = "create";

	private static final String TEST = "test";

	@Parameter(names = { "-h", "--help" }, help = true, description = "this message")
	private boolean help;

	private static CreateCommand cc = new CreateCommand();
	private static TestCommand tc = new TestCommand();

	public static void main(String... args) throws IOException, MavenInvocationException, XPathExpressionException {

		Cli cli = new Cli();
		JCommander jc = new JCommander(cli);
		jc.setProgramName("run.sh");
		jc.addCommand(TEST, tc);
		jc.addCommand(CREATE, cc);
		if (cli.help || args.length == 0) {
			jc.usage();
			return;
		}

		try {
			jc.parse(args);
			switch (jc.getParsedCommand()) {
			case TEST:
				MavenUtils.mavenInvoke(tc.platform);
				break;
			case CREATE:
				new SolutionBuilder().className(cc.classname).subdomain(cc.subdomain).domain(cc.domain)
						.platform(cc.platform).description(cc.description).build();
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		/*
		 * if (line.hasOption("unsolved")) { String[] values =
		 * line.getOptionValues("u");
		 * System.out.println(HackerrankJson.unsolved(domain, subdomain)); }
		 * 
		 * if (line.hasOption("generate-all")) { String[] values =
		 * line.getOptionValues("g"); for (String challenge :
		 * HackerrankJson.unsolved(domain, subdomain)) { String
		 * challengeNormalized = StringUtils.normalize(challenge); String
		 * domainNormalized = StringUtils.normalize(domain); String
		 * subdomainNormalized = StringUtils.normalize(subdomain);
		 * create(challengeNormalized, domainNormalized, subdomainNormalized,
		 * domain + subdomain + challenge); } }
		 * 
		 * if (line.hasOption("list")) { HackerrankPageReader.list(); }
		 * 
		 * if (line.hasOption("version")) { VersionUtils.printVersion(); }
		 * 
		 */

		// } catch (ParseException exp) {
		// System.err.println(exp.getMessage());
		// System.err.println("for help use: run.sh -h");
		// HelpFormatter formatter = new HelpFormatter();
		// formatter.printHelp("run.sh", "qwe", options, "zxc", true);
		// }

	}

	/*
	 * private static Options createOptions() { Option help = new Option("h",
	 * "help", false, "print this message"); Option version = new Option("v",
	 * "version", false, "print the version information and exit"); Option
	 * domain =
	 * Option.builder("d").required().longOpt("domain").hasArg().argName("NAME")
	 * .desc("domain name (e.g. java)").build(); Option subdomain =
	 * Option.builder("s").required().longOpt("subdomain").hasArg().argName(
	 * "NAME") .desc("subdomain name (e.g. java-advanced)").build();
	 * 
	 * Option run =
	 * Option.builder("r").longOpt("run").desc("run all tests using maven").
	 * build(); Option create =
	 * Option.builder("c").longOpt("create").argName("CLASS").hasArg()
	 * .desc("create solution from template").build(); Option list =
	 * Option.builder("l").longOpt("list").
	 * desc("list available domains |- subdomains (slug)").build(); Option uns =
	 * Option.builder("u").longOpt("unsolved").
	 * desc("list yet unsolved challenges").build(); Option gen =
	 * Option.builder("g").longOpt("generate-all").
	 * desc("generate template for all unsolved challenges") .build(); Option
	 * platf =
	 * Option.builder("p").required().longOpt("NAME").numberOfArgs(1).argName(
	 * "paltform") .valueSeparator(',').hasArgs().
	 * desc("tests platform (hackerrank, codility)").build();
	 * 
	 * Options options = new Options(); options.addOption(help);
	 * options.addOption(version); options.addOption(platf);
	 * options.addOption(domain); options.addOption(subdomain);
	 * options.addOption(run); options.addOption(create);
	 * options.addOption(list); options.addOption(uns); options.addOption(gen);
	 * return options; }
	 */
}
