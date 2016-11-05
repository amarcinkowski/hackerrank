package io.github.amarcinkowski.solutionframework.command;

import com.beust.jcommander.Parameter;

public class BaseCommand {
	@Parameter(names = { "-p", "--platform" }, description = "platform name e.g. hackerrank, codility", required = true)
	public String platform;
}