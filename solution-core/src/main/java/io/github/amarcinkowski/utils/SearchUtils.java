package io.github.amarcinkowski.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchUtils {

	public static List<String> findAllMultiline(String pattern, String content) {
		List<String> list = new ArrayList<>();
		Matcher m = Pattern.compile(pattern, Pattern.DOTALL).matcher(content);
		while (m.find()) {
			list.add(m.group(1));
		}
		return list;
	}

}
