package io.github.amarcinkowski.solutionframework.command;

import com.beust.jcommander.Parameter;

public class CreateCommand extends BaseCommand {
	@Parameter(names = { "-d", "--domain" }, required = true)
	public String domain;
	@Parameter(names = { "-s", "--subdomain" }, required = true)
	public String subdomain;
	@Parameter(names = { "-c", "--classname" }, required = true)
	public String classname;
	@Parameter(names = { "-t", "--description" })
	public String description;
}