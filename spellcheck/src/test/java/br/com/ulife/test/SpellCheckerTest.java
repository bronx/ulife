package br.com.ulife.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.ulife.spellcheck.SpellChecker;

public class SpellCheckerTest {

	private SpellChecker instance;
	
	@Before
	public void setup() {
		this.instance = new SpellChecker();
	}
	
	@Test
	public void testSingleMispelledWord() {
		assertEquals(1, instance.countMispelledWordsIn("Esta frase possui apnas um erro ortográfico").intValue());
	}
	
	@Test
	public void testNoMispelledWords() {
		assertEquals(0, instance.countMispelledWordsIn("Esta frase não possui erro ortográfico").intValue());
	}
	
	@Test
	public void testMispelledWordsInCrazyString() {
		assertEquals(3, instance.countMispelledWordsIn("Estla \"frase\" não!, .    possgui, erro. ortográico").intValue());
	}
	
	@Test
	public void testNoMispelledWordsInCrazyString() {
		assertEquals(0, instance.countMispelledWordsIn("Esta \"frase\" não!, .    possui, erros. ortográficos").intValue());
	}	
	
}
