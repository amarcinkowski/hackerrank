package io.github.amarcinkowski.utils;

import java.text.Normalizer;

import com.google.common.base.CaseFormat;

public class StringUtils {
	
	private static final String NONASCII = "[^\\x00-\\x7F]";

	public static String normalize(String string) {
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		return string.replaceAll(NONASCII, "").replaceAll("[ ]", "");
	}
	
	public static String packagify(String string) {
		return normalize(string).replaceAll("\\-", "").toLowerCase();
	}
	
	public static String camelify(String string) {
		string = string.replaceAll(" ", "-").replaceAll(NONASCII, "");
		return CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, string);
	}

}
