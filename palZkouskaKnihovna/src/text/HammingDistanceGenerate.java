/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Thang Do
 */
public class HammingDistanceGenerate {

	String pattern;
	String input;

	//reprezentace jednotlivých znaku za int pro přechod na další stavy
	HashMap<Character, Integer> alphabetCoding;

	//znaky v abecedě
	HashSet<String> alphabet;

	int[][] automat;

	public HammingDistanceGenerate(String pattern, String input, HashSet<String> alphabet, int maxDistance) {
		this.pattern = pattern;
		this.input = input;
		this.alphabet = alphabet;
		initAlphabetCoding();
		createAutomat(input, pattern, maxDistance);
	}

	void createAutomat(String input, String pattern, int maxDistance) {

		//v nule se start 
		int patternLength = pattern.length() + 1;
		// +1 protože v 0 řádku je obsažen celý pattern 
		int distance = maxDistance + 1;

		automat = new int[distance][patternLength];

		int stateCounter = 1;
		for (int i = 0; i < distance; i++) {
			for (int j = i; j < patternLength; j++) {
				automat[i][j] = stateCounter;
				stateCounter++;
			}
		}
	}

	boolean matchPattern() {
		int actualState = 0;
		int floor = 0;
		for (int i = 0; i < input.length(); i++) {
			if (actualState == 4) {
				return true;
			}
			if (alphabetCoding.containsKey(input.charAt(i))) {
				System.out.println("read-> " + input.charAt(i));
				actualState = getNextState(actualState, input.charAt(i));
			} else {
				actualState = 0;
			}

		}

		return false;
	}

	private void initAlphabetCoding() {
		alphabetCoding = new HashMap<>();
		for (int i = 0; i < pattern.length(); i++) {
			alphabetCoding.put(pattern.charAt(i), i + 1);
		}
	}

	int getNextState(int actualState, char val) {
		return automat[actualState][alphabetCoding.get(val)];
	}

}
