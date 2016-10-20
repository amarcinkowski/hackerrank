package io.github.amarcinkowski.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtils {

	public static void setLevel(ch.qos.logback.classic.Level level) {
		ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory
				.getLogger(Logger.ROOT_LOGGER_NAME);
		logger.setLevel(level);
	}

}
