package io.github.amarcinkowski.utils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	private static final String NONASCII = "[^\\x00-\\x7F]";

	public static String normalize(String string) {
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		return string.replaceAll(NONASCII, "").replaceAll("[ -]", "");
	}

	public static List<String> findAllMultiline(String pattern, String content) {
		List<String> list = new ArrayList<>();
		Matcher m = Pattern.compile(pattern, Pattern.DOTALL).matcher(content);
		while (m.find()) {
			list.add(m.group(1));
		}
		return list;
	}

}
