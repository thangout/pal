/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.util.ArrayList;

/**
 *
 * @author Thang Do
 */
public class TextSearch {

	int N, M, cI, cD, cR;
	String text;
	String pattern;
	int[][] levenstein;

	public TextSearch(int N, int M, int cI, int cD, int cR, String text, String pattern) {
		this.N = N;
		this.M = M;
		this.cI = cI;
		this.cD = cD;
		this.cR = cR;
		this.text = text;
		this.pattern = pattern;
		levenstein(text, pattern);
		findSubstring();
	}

	void levenstein(String text, String pattern) {
		levenstein = new int[M + 1][N + 1];

		for (int i = 0; i < M + 1; i++) {
			levenstein[i][0] = i;
		}

		for (int i = 0; i < N + 1; i++) {
			levenstein[0][i] = 0;
		}

		for (int i = 1; i < M + 1; i++) {
			for (int k = 1; k < N + 1; k++) {
				if (i < M) {
					levenstein[i][k] = findMin(
						levenstein[i - 1][k] + cD,
						levenstein[i][k - 1] + cI,
						levenstein[i - 1][k - 1] + (pattern.charAt(i - 1) == text.charAt(k - 1) ? 0 : cR)
					);
				} else {
					levenstein[i][k] = Math.min(
						levenstein[i - 1][k] + cD,
						levenstein[i - 1][k - 1] + (pattern.charAt(i - 1) == text.charAt(k - 1) ? 0 : cR)
					);
				}
			}
		}

	}

	void findSubstring() {
		int min;
		min = levenstein[M][M];
		int indexOfMin = M;
		for (int i = M; i < N + 1; i++) {
			if (levenstein[M][i] < min) {
//				System.out.println("found" + levenstein[M][i]);
				min = levenstein[M][i];
				indexOfMin = i;
			}
		}
		ArrayList<Integer> minimums = new ArrayList<>();
		minimums.add(indexOfMin);
		for (int i = indexOfMin + 1; i < N + 1; i++) {
			if (levenstein[M][i] == min) {
				minimums.add(i);
//				System.out.println(i);
			}
		}
		int minimumLength = -1;
		int leftIndex = -1;

		for (int i = 0; i < minimums.size(); i++) {
			int fooLeftIndex = levensteinReverse(minimums.get(i));
			int fooMinLength = minimums.get(i) - fooLeftIndex + 1;

//			System.out.println(fooLeftIndex + " left index");
//			System.out.println(fooMinLength + " min Length");
//			System.out.println(fooLeftIndex);
//			System.out.println(fooMinLength);

			if (i == 0) {
				minimumLength = fooMinLength;
				leftIndex = fooLeftIndex;
			} else {
				if (fooMinLength < minimumLength) {
					minimumLength = fooMinLength;
					leftIndex = fooLeftIndex;
				}
			}

		}
		System.out.println(leftIndex - 1 + " " + minimumLength + " " + min);
	}

	int levensteinReverse(int start) {
//		System.out.println(start);
		int i = M;
		int k = start;
		while (i > 1) {
			if (k == 1) {
				break;
			}
//			System.out.println(i);
//			System.out.println(k);
			int movedCode = findIndexMin(
				levenstein[i - 1][k] + cD,
				levenstein[i][k - 1] + cI,
				levenstein[i - 1][k - 1] + (pattern.charAt(i - 1) == text.charAt(k - 1) ? 0 : cR)
			);
			if (movedCode == 1) {
				i--;
			} else if (movedCode == 2) {
				k--;
			} else {
				i--;
				k--;
			}
		}
		return k;
	}

	private int findMin(int a, int b, int c) {
		int min = a;

		if (b < min) {
			min = b;
		}

		if (c < min) {
			min = c;
		}

		return min;
	}

	private int findIndexMin(int a, int b, int c) {
		int min = a;
		int index = 1;
		if (b < min) {
			min = b;
			index = 2;
		}

		if (c < min) {
			min = c;
			index = 3;
		}

		return index;
	}

	void printLevenstein() {
		for (int i = 0; i < M + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				System.out.print(levenstein[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("");

	}

}
