package io.github.amarcinkowski.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

public class TemplateUtils {

	public static final String SOLUTION_TWIG_TEMPLATE = "solution-core/src/main/resources/solution.twig";
	public static final String TEST_TWIG_TEMPLATE = "solution-core/src/main/resources/test.twig";

	private static final String PLATFORM = "PLATFORM";
	private static final String METHOD = "METHOD";
	private static final String DESCRIPTION = "DESCRIPTION";
	private static final String CLASS = "CLASS";
	private static final String DOMAIN = "DOMAIN";
	private static final String GROUP = "GROUP";
	private static final String PACKAGE = "PACKAGE";
	private static final String CLASSNAME = "CLASSNAME";


	private static JtwigTemplate getTemplate(String twigFile) {
		return JtwigTemplate.fileTemplate(new File(twigFile));
	}

	public static String getRenderedTemplate(String className, String packageName) {
		JtwigModel model = JtwigModel.newModel().with(CLASSNAME, className).with(PACKAGE, packageName);
		return getTemplate(SOLUTION_TWIG_TEMPLATE).render(model);
	}

	public static String getRenderedTemplate(String platform, String domainName, String groupName, String className,
			String taskDescription) throws IOException {
		JtwigModel model = JtwigModel.newModel().with(DOMAIN, domainName).with(GROUP, groupName)
				.with(PLATFORM, platform).with(DESCRIPTION, taskDescription).with(CLASS, className)
				.with(METHOD, StringUtils.uncapitalize(className));
		return getTemplate(TEST_TWIG_TEMPLATE).render(model);
	}

}
