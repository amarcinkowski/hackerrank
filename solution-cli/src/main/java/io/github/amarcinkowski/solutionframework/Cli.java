package io.github.amarcinkowski.solutionframework;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.apache.maven.shared.invoker.MavenInvocationException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import io.github.amarcinkowski.solutionframework.browser.PageReader;
import io.github.amarcinkowski.solutionframework.command.CreateCommand;
import io.github.amarcinkowski.solutionframework.command.ListCommand;
import io.github.amarcinkowski.solutionframework.command.TestCommand;
import io.github.amarcinkowski.utils.MavenUtils;

public class Cli {

	private static final String LIST = "list";

	private static final String RUN_SH = "run.sh";

	private static final String CREATE = "create";

	private static final String TEST = "test";

	@Parameter(names = { "-h", "--help" }, help = true, description = "this message")
	private boolean help;

	@Parameter(names = { "-v", "--version" }, description = "version information")
	private boolean version;

	private static CreateCommand cc = new CreateCommand();
	private static TestCommand tc = new TestCommand();
	private static ListCommand lc = new ListCommand();

	public static void main(String... args) throws IOException, MavenInvocationException, XPathExpressionException {

		Cli cli = new Cli();
		JCommander jc = new JCommander(cli);
		jc.setProgramName(RUN_SH);
		jc.addCommand(TEST, tc);
		jc.addCommand(CREATE, cc);
		jc.addCommand(LIST, lc);

		if (cli.help || args.length == 0) {
			jc.usage();
			return;
		}

		if (cli.version) {

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
			case LIST:
				switch (lc.platform) {
				case "hackerrank":
					String source = PageReader.source(PageReader.HACKERRANK_URL);
					PageReader.printList(source);
					break;
				case "codility":
					source = PageReader.source(PageReader.CODILITY_URL);
					System.out.println(source);
					break;
				}
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		/*
		 * if (line.hasOption("unsolved")) { String[] values =
		 * 
		 * System.out.println(HackerrankJson.unsolved(domain, subdomain)); }
		 * 
		 * if (line.hasOption("generate-all")) { String[] values =
		 * line.getOptionValues("g"); for (String challenge :
		 * HackerrankJson.unsolved(domain, subdomain)) { String
		 * 
		 * challengeNormalized = StringUtils.normalize(challenge); String
		 * domainNormalized = StringUtils.normalize(domain); String
		 * subdomainNormalized = StringUtils.normalize(subdomain);
		 * 
		 * create(challengeNormalized, domainNormalized, subdomainNormalized,
		 * domain + subdomain + challenge); } }
		 * 
		 */

	}
	/*
	 * Option.builder("c").longOpt("create").argName("CLASS").hasArg()
	 * .desc("create solution from template").build(); Option list =
	 * Option.builder("l").longOpt("list").
	 * desc("list available domains |- subdomains (slug)").build(); Option uns =
	 * Option.builder("u").longOpt("unsolved").
	 * desc("list yet unsolved challenges").build(); Option gen =
	 * Option.builder("g").longOpt("generate-all").
	 * desc("generate template for all unsolved challenges") .build(); Option
	 */
}
