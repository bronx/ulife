package br.com.ulife.spellbattle.spellcheck;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.languagetool.JLanguageTool;
import org.languagetool.language.BrazilianPortuguese;
import org.languagetool.rules.Rule;
import org.languagetool.rules.SentenceWhitespaceRule;
import org.languagetool.rules.pt.PreReformPortugueseCompoundRule;
import org.languagetool.rules.spelling.hunspell.HunspellNoSuggestionRule;


public class SpellChecker {
	
	public Integer countMispelledWordsIn(String input) {
		
		JLanguageTool tool = new JLanguageTool(new BrazilianPortuguese() {
			  @Override
			  public List<Rule> getRelevantRules(ResourceBundle messages) throws IOException {
			    return Arrays.asList(
			            new HunspellNoSuggestionRule(messages, this),
			            new SentenceWhitespaceRule(messages),
			            new PreReformPortugueseCompoundRule(messages)
			    );
			  }
		});

		try {
			return tool.check(input).size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		
		if (args == null || args.length == 0) {
			System.out.println("Please inform at least 1 (one) sentence (in portuguese).");
			return;
		}
		
		SpellChecker checker = new SpellChecker();
		for (String arg : args) {
			System.out.println("Sentence '" + arg + "' has " + checker.countMispelledWordsIn(arg) + " orthographical mistakes.");
		}
		
	}
	
}
