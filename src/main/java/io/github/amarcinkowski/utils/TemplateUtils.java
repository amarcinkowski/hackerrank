package io.github.amarcinkowski.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

public class TemplateUtils {

	public static final String METHOD = "METHOD";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String CLASS = "CLASS";
	public static final String DOMAIN = "DOMAIN";
	public static final String GROUP = "GROUP";
	public static final String PACKAGE = "PACKAGE";
	public static final String CLASSNAME = "CLASSNAME";

	public static final String SOLUTION_TWIG_TEMPLATE = "src/main/resources/solution.twig";
	public static final String TEST_TWIG_TEMPLATE = "src/main/resources/test.twig";

	private static JtwigTemplate getTemplate(String twigFile) {
		return JtwigTemplate.fileTemplate(new File(twigFile));
	}

	public static String getRenderedTemplate(String className, String packageName) {
		JtwigModel model = JtwigModel.newModel().with(CLASSNAME, className).with(PACKAGE, packageName);
		return getTemplate(SOLUTION_TWIG_TEMPLATE).render(model);
	}

	public static String getRenderedTemplate(String domainName, String groupName, String className,
			String hackerrankDescription) throws IOException {
		JtwigModel model = JtwigModel.newModel().with(DOMAIN, domainName).with(GROUP, groupName)
				.with(DESCRIPTION, hackerrankDescription).with(CLASS, className)
				.with(METHOD, StringUtils.uncapitalize(className));
		return getTemplate(TEST_TWIG_TEMPLATE).render(model);
	}

}
