package com.hackerrank.java;

import org.apache.log4j.Logger;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TestSettings implements TestRule {

	private final static Logger logger = Logger.getLogger(TestSettings.class);
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