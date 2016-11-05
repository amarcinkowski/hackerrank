package io.github.amarcinkowski.utils;

import java.io.File;
import java.util.Collections;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

/**
 * The Class MavenUtils.
 */
public class MavenUtils {

	private static final String LOGNAME = "LOGNAME";

	private static final String TRAVIS = "travis";

	private static final String USR_LOCAL_MAVEN = "/usr/local/maven";

	/** The Constant MVN_LOG_LEVEL. */
	private static final String MVN_LOG_LEVEL = "-Dorg.slf4j.simpleLogger.defaultLogLevel=warn";

	/** The Constant MVN_FAILED. */
	private static final String MVN_FAILED = "mvn failed";

	/** The Constant USR_SHARE_MAVEN. */
	private static final String USR_SHARE_MAVEN = "/usr/share/maven";

	/** The Constant MAVEN_HOME. */
	private static final String MAVEN_HOME = "maven.home";

	/** The Constant TEST. */
	private static final String TEST = "test";

	/** The Constant POM_XML. */
	private static final String POM_XML = "%s-solutions/pom.xml";

	/**
	 * Maven invoke.
	 *
	 * @param platform
	 *            the platform
	 * @throws MavenInvocationException
	 *             the maven invocation exception
	 */
	public static void mavenInvoke(String platform) throws MavenInvocationException {
		Invoker invoker = new DefaultInvoker();
		setEnv(invoker);
		InvocationResult ir = invoker.execute(getRequest(platform));
		if (ir.getExitCode() != 0) {
			System.err.println(MVN_FAILED);
		}
	}

	private static void setEnv(Invoker invoker) {
		if (System.getenv(LOGNAME).equals(TRAVIS)) {
			invoker.setMavenHome(new File(USR_LOCAL_MAVEN));
		} else {
			System.setProperty(MAVEN_HOME, USR_SHARE_MAVEN);
		}
	}

	/**
	 * Gets the request.
	 *
	 * @param platform
	 *            the platform
	 * @return the request
	 */
	private static InvocationRequest getRequest(String platform) {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File(String.format(POM_XML, platform)));
		request.setGoals(Collections.singletonList(TEST));
		request.setMavenOpts(MVN_LOG_LEVEL);
		return request;
	}

}
