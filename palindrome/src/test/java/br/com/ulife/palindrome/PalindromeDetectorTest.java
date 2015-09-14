package br.com.ulife.palindrome;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PalindromeDetectorTest {

	private PalindromeDetector instance;
	
	@Before
	public void setup() {
		instance = new PalindromeDetector();
	}
	
	@Test
	public void testWithNullObject() {
		assertFalse(instance.isPalindrome(null));
	}
	
	@Test
	public void testWithEmptyString() {
		assertFalse(instance.isPalindrome(""));
	}	
	
	@Test
	public void testWithSingleCharacterString() {
		assertTrue(instance.isPalindrome("a"));
	}
	
	@Test
	public void testWithNoPalindromeString() {
		assertFalse(instance.isPalindrome("test"));
	}
	
	@Test
	public void testWithPalindrome() {
		assertTrue(instance.isPalindrome("arara"));
	}	
	
}
