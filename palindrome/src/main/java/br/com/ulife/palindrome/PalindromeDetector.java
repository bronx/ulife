package br.com.ulife.palindrome;

import org.apache.commons.lang3.StringUtils;

public class PalindromeDetector {
	 
	 public Boolean isPalindrome(final String input) {
		 if (input == null || input.isEmpty()) return false;
		 
		 String textWithoutAccents = StringUtils.stripAccents(input); 
		 
		 for (int i = 0, j = textWithoutAccents.length() - 1; i <= j; i++, j--) {
			 if (textWithoutAccents.charAt(i) != textWithoutAccents.charAt(j)) return false;
		 }
		 
		 return true;
	 }

	 public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.out.println("Word(s) missing. Please inform at least 1 (one) word.");
			return;
		}
		
		PalindromeDetector detector = new PalindromeDetector();
		for (String word : args) {
			System.out.println("Word '" + word + "' " + (detector.isPalindrome(word) ? "is" : "isn't") + " a palindrome.");
		}
	}
}
