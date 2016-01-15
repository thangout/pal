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
public class HammingDistanceDynamic {

	String pattern;
	String input;
	int[][] hamming;

	public HammingDistanceDynamic(String pattern, String input) {
		this.pattern = pattern;
		this.input = input;
		this.hamming = new int[pattern.length() + 1][input.length() + 1];
		initHamming();
		createHamming();
	}

	void createHamming() {
		for (int i = 1; i < pattern.length() + 1; i++) {
			for (int j = 1; j < input.length() + 1; j++) {
				if (pattern.charAt(i - 1) == input.charAt(j - 1)) {
					hamming[i][j] = hamming[i - 1][j - 1];
				} else {
					hamming[i][j] = hamming[i - 1][j - 1] + 1;
				}
			}
		}

	}

	void initHamming() {
		for (int i = 0; i < input.length(); i++) {
			hamming[0][i] = 0;

		}

	}

	void print() {
		System.out.print("\t");
		for (int i = 0; i < input.length(); i++) {
			System.out.print(input.charAt(i) + "\t");
		}
		System.out.println("");

		for (int i = 0; i < pattern.length() + 1; i++) {
			for (int j = 0; j < input.length() + 1; j++) {
				System.out.print(hamming[i][j] + "\t");
			}
			System.out.println("");
		}
	}
}
