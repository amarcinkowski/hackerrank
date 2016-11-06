package io.github.amarcinkowski.solutionframework.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "generates all (yet unsolved) solution files from templates")
public class GenerateCommand extends BaseCommand {
	@Parameter(names = { "-d", "--domain" }, required = true)
	public String domain;
	@Parameter(names = { "-s", "--subdomain" }, required = true)
	public String subdomain;
}