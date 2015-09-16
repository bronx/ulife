package br.com.ulife.spellbattle.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tweet {

	private static final Logger LOGGER = LoggerFactory.getLogger(Tweet.class);
	
	private String text;

	public String getText() {
		LOGGER.debug(Thread.currentThread().getName() + " - Tweet: " + text);
		return text.replaceAll("[\uD83C-\uDBFF\uDC00-\uDFFF]+", "") //Removes emojis (not so good)
					.replaceAll("#[^\\s]+", "") // Removes hashtags
					.replaceAll("@[^\\s]+",	"") // Removes citation
					.replaceAll("((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)", "") // Removes urls
					.trim();
	}

	public void setPureText(String text) {
		this.text = text;
	}	
	
}
