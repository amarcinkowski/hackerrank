package io.gihtub.amarcinkowski.hackerrank.java;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSettings implements TestRule {

	public static final Logger logger = LoggerFactory.getLogger(TestSettings.class);
	String className, methodName;

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				className = description.getClassName();
				methodName = description.getMethodName();
				String str = String.format("Running test %s in %s", methodName, className);
				logger.info(str);
				base.evaluate();
			}
		};
	}
}