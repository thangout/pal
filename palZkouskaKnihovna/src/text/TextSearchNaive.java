/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

/**
 *
 * @author Thang Do
 */
public class TextSearchNaive {

	String input = "Hello this is Ana";

	//return true if contains
	boolean search(String pattern, String input) {
		int patternPosition = 0;

		while (patternPosition + pattern.length() < input.length()) {
			int matchCounter = 0;
			for (int i = 0; i < pattern.length(); i++) {
				if (input.charAt(i + patternPosition) == pattern.charAt(i)) {
					matchCounter++;
				}
			}
			if (matchCounter == pattern.length()) {
				return true;
			}
			patternPosition++;
		}
		return false;
	}
}
