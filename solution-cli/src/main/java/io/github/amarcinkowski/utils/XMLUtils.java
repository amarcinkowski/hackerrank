package io.github.amarcinkowski.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Document.OutputSettings.Syntax;

public class XMLUtils {

	public static String toXHTML(String html) {
		html = html.replaceAll("(?s)<script>.*?</script>", "<!-- removed scripts --!>");
		final Document document = Jsoup.parse(html);
		document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
		return document.html();
	}

}
