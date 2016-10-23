package io.github.amarcinkowski.utils;

import java.text.Normalizer;

public class StringUtils {
	
	private static final String NONASCII = "[^\\x00-\\x7F]";

	public static String normalize(String string) {
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		return string.replaceAll(NONASCII, "").replaceAll("[ -]", "");
	}

}
