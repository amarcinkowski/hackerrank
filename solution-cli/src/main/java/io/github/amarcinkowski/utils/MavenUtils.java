package io.github.amarcinkowski.utils;

import java.io.File;
import java.util.Collections;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

// TODO: Auto-generated Javadoc
/**
 * The Class MavenUtils.
 */
public class MavenUtils {

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
	 * @param platform the platform
	 * @throws MavenInvocationException the maven invocation exception
	 */
	public static void mavenInvoke(String platform) throws MavenInvocationException {
		setEnv();
		Invoker invoker = new DefaultInvoker();
		InvocationResult ir = invoker.execute(getRequest(platform));
		if (ir.getExitCode() != 0) {
			System.err.println(MVN_FAILED);
		}
	}

	/**
	 * Sets the local env.
	 */
	private static void setEnv() {
		if (System.getenv("LOGNAME").equals("amarcinkowski")) {
			System.setProperty(MAVEN_HOME, USR_SHARE_MAVEN);
		}
	}

	/**
	 * Gets the request.
	 *
	 * @param platform the platform
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
