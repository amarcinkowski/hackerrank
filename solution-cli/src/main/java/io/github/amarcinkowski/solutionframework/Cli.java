package io.github.amarcinkowski.solutionframework;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.apache.maven.shared.invoker.MavenInvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import io.github.amarcinkowski.solutionframework.browser.HackerrankJson;
import io.github.amarcinkowski.solutionframework.browser.PageReader;
import io.github.amarcinkowski.solutionframework.command.CreateCommand;
import io.github.amarcinkowski.solutionframework.command.GenerateCommand;
import io.github.amarcinkowski.solutionframework.command.ListCommand;
import io.github.amarcinkowski.solutionframework.command.TestCommand;
import io.github.amarcinkowski.utils.MavenUtils;
import io.github.amarcinkowski.utils.PopertiesUtils;
import io.github.amarcinkowski.utils.StringUtils;

public class Cli {

	private final static Logger logger = LoggerFactory.getLogger(Cli.class);

	private static final String CODILITY = "codility";
	private static final String HACKERRANK = "hackerrank";
	private static final String LIST = "list";
	private static final String RUN_SH = "run.sh";
	private static final String CREATE = "create";
	private static final String TEST = "test";

	@Parameter(names = { "-h", "--help" }, help = true, description = "this message")
	private boolean help;

	@Parameter(names = { "-v", "--version" }, description = "version information")
	private boolean version;

	public static void main(String... args) throws IOException, MavenInvocationException, XPathExpressionException {

		CreateCommand cc = new CreateCommand();
		TestCommand tc = new TestCommand();
		ListCommand lc = new ListCommand();
		GenerateCommand gc = new GenerateCommand();

		Cli cli = new Cli();
		JCommander jc = new JCommander(cli);
		jc.setProgramName(RUN_SH);
		jc.addCommand(TEST, tc);
		jc.addCommand(CREATE, cc);
		jc.addCommand(LIST, lc);
		jc.addCommand("generate", gc);

		try {
			jc.parse(args);

			if (cli.help || args.length == 0) {
				jc.usage();
				return;
			}

			if (cli.version) {
				PopertiesUtils.printVersion();
				return;
			}

			switch (jc.getParsedCommand()) {
			case TEST:
				MavenUtils.mavenInvoke(tc.platform);
				break;
			case CREATE:
				new SolutionBuilder().className(cc.classname).subdomain(cc.subdomain).domain(cc.domain)
						.platform(cc.platform).description(cc.description).build();
			case LIST:
				switch (lc.platform) {
				case HACKERRANK:
					String source = PageReader.source(PageReader.HACKERRANK_URL);
					PageReader.printList(source);
					break;
				case CODILITY:
					source = PageReader.source(PageReader.CODILITY_URL);
					System.out.println(source);
					break;
				}
				break;
			case "generate":
				for (String challenge : HackerrankJson.unsolved(gc.domain, gc.subdomain)) {
					String classname = StringUtils.camelify(StringUtils.normalize(challenge));
					logger.info(String.format("%s => %s", challenge, classname));
					new SolutionBuilder().className(classname).subdomain(gc.subdomain).domain(gc.domain)
							.platform(gc.platform).description(challenge).build();
				}
				break;

			default:
				break;
			}
		} catch (Exception e) {
			if (e.getMessage() != null) {
				System.err.println(e.getMessage());
			} else {
				e.printStackTrace();
			}
		}

	}
}
