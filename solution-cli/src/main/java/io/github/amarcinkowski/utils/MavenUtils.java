package io.github.amarcinkowski.utils;

import java.io.File;
import java.util.Collections;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class MavenUtils {

	private static final String MVN_LOG_LEVEL = "-Dorg.slf4j.simpleLogger.defaultLogLevel=warn";
	private static final String MVN_FAILED = "mvn failed";
	private static final String USR_SHARE_MAVEN = "/usr/share/maven";
	private static final String MAVEN_HOME = "maven.home";
	private static final String TEST = "test";
	private static final String POM_XML = "%s-solutions/pom.xml";

	public static void mavenInvoke(String platform) throws MavenInvocationException {
		setEnv();
		Invoker invoker = new DefaultInvoker();
		InvocationResult ir = invoker.execute(getRequest(platform));
		if (ir.getExitCode() != 0) {
			System.err.println(MVN_FAILED);
		}
	}

	private static void setEnv() {
		System.setProperty(MAVEN_HOME, USR_SHARE_MAVEN);
	}

	private static InvocationRequest getRequest(String platform) {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File(String.format(POM_XML, platform)));
		request.setGoals(Collections.singletonList(TEST));
		request.setMavenOpts(MVN_LOG_LEVEL);
		return request;
	}

}
