/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Thang Do
 */
public class Automat {

	int S, A, F, P, N, L;
	int[][] states;
	String[] examples;
	HashMap<Character, Integer> alphabet;
	boolean[][] finalStates;

	public Automat(int S, int A, int F, int P, int N, int L, int[][] states, String[] examples) {
		this.S = S;
		this.A = A;
		this.F = F;
		this.P = P;
		this.N = N;
		this.L = L;
		this.states = states;
		this.examples = examples;
		finalStates = new boolean[S][S];
		initAlphabet();
	}

	void readExample() {
		for (int s = 0; s < S; s++) {
			int actualState = s;
			for (int i = 0; i < examples.length; i++) {
				for (int j = 0; j < examples[i].length(); j++) {
					actualState = getNextState(actualState, examples[i].charAt(j));
				}
				finalStates[s][actualState] = true;
				actualState = s;
//				System.out.print(actualState + "_");
				if (checkNumOfFinalStates(s) > F) {
					break;	
				}
			}
		}
	}

	int checkNumOfFinalStates(int row) {
		int count = 0;
		for (int i = 0; i < S; i++) {
			if (finalStates[row][i]) {
				count++;
			}
		}
		return count;
	}

	void printSolution() {
		for (int i = 0; i < S; i++) {
			int counter = 0;
			String res = "" + i;
			for (int j = 0; j < S; j++) {
				if (finalStates[i][j]) {
					res = res + " " + j;
					counter++;
				}
			}
			if (counter == F) {
			System.out.println(res);
			}
		}

	}

	int getNextState(int actualState, char val) {
		return states[actualState][alphabet.get(val)];
	}

	void initAlphabet() {
		alphabet = new HashMap<>();
		int index = 0;
		for (int i = 97; i < 123; i++) {
			alphabet.put((char) i, index);
			index++;
		}

	}

}
