package io.github.amarcinkowski.solutionframework.command;

import com.beust.jcommander.Parameter;

public class GenerateCommand extends BaseCommand {
	@Parameter(names = { "-d", "--domain" }, required = true)
	public String domain;
	@Parameter(names = { "-s", "--subdomain" }, required = true)
	public String subdomain;
}