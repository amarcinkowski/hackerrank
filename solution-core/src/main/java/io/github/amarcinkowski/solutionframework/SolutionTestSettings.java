package io.github.amarcinkowski.solutionframework;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolutionTestSettings implements TestRule {

	public static final Logger logger = LoggerFactory.getLogger(SolutionTestSettings.class);
	String className, methodName;

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				className = description.getClassName();
				methodName = description.getMethodName();
				String str = String.format(" = %-20s [%s]", methodName, className.substring(className.lastIndexOf(".") + 1));
				logger.debug(".");
				logger.debug(str);
				base.evaluate();
			}
		};
	}
}