package io.github.amarcinkowski.hackerrank;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HackerrankJson {

	private static final String NEWLINE = "\n";
	private static final String CATEGORIES = "categories";
	private static final String CONTEST = "contest";
	private static final String CHILDREN = "children";
	private static final String SLUG = "slug";
	private static final String NAME = "name";
	private static final String FORMAT = " |- %-34s <%s,%s>\n";

	private static JsonArray getCategories(String json) {
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json);
		return je.getAsJsonObject().get(CONTEST).getAsJsonObject().get(CATEGORIES).getAsJsonArray();
	}

	public static String hackerrankJsonToList(String json) {
		JsonArray ja = getCategories(json);
		return parseCategories(ja);
	}

	private static String get(JsonObject obj, String name) {
		return obj.get(name).getAsString();
	}

	private static String get(JsonElement el, String name) {
		return get(el.getAsJsonObject(), name);
	}

	private static String parseChildren(JsonArray children, String slug) {
		final StringBuilder sb = new StringBuilder();
		children.forEach(ch -> sb.append(String.format(FORMAT, get(ch, NAME), slug, get(ch, SLUG))));
		return sb.toString();
	}

	private static String parseCategories(JsonArray ja) {
		final StringBuilder sb = new StringBuilder();
		for (JsonElement element : ja) {
			JsonObject obj = element.getAsJsonObject();
			String slug = get(obj, SLUG);
			sb.append(get(obj, NAME));
			sb.append(NEWLINE);
			JsonArray children = obj.get(CHILDREN).getAsJsonArray();
			sb.append(parseChildren(children, slug));
		}
		return sb.toString();
	}

}