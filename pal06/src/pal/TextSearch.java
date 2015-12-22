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
	int[][] levensteinPath;

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
		levensteinPath = new int[M + 1][N + 1];

		for (int i = 0; i < M + 1; i++) {
			levenstein[i][0] = i*cD;
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
					levensteinPath[i][k] = findIndexMin(
						levenstein[i - 1][k] + cD,
						levenstein[i][k - 1] + cI,
						levenstein[i - 1][k - 1] + (pattern.charAt(i - 1) == text.charAt(k - 1) ? 0 : cR));
				} else {
					levenstein[i][k] = Math.min(
						levenstein[i - 1][k] + cD,
						levenstein[i - 1][k - 1] + (pattern.charAt(i - 1) == text.charAt(k - 1) ? 0 : cR)
					);

					levensteinPath[i][k] = findIndexMin(
						levenstein[i - 1][k] + cD,
						Integer.MAX_VALUE,
						levenstein[i - 1][k - 1] + (pattern.charAt(i - 1) == text.charAt(k - 1) ? 0 : cR));
				}
			}
		}

	}

	void findSubstring() {
		int indexOfMin = -1;
		int start = 0;
		int minLeft = -1;
		while (minLeft == -1) {
			indexOfMin = findMinInLevenstein(start);
			minLeft = levensteinReverse(indexOfMin);
			start = indexOfMin;
		}

		ArrayList<Integer> minimums = new ArrayList<>();
		minimums.add(indexOfMin);
		for (int i = indexOfMin + 1; i < N; i++) {
			if (levenstein[M][i] == levenstein[M][indexOfMin]) {
				minimums.add(i);
//				System.out.println(i);
			}
		}
		int minimumLength = -1;
		int leftIndex = -5;

		for (int i = 0; i < minimums.size(); i++) {
			int fooLeftIndex = levensteinReverse(minimums.get(i));
			int fooMinLength = minimums.get(i) - fooLeftIndex;
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
		System.out.println(leftIndex + " " + minimumLength + " " + levenstein[M][indexOfMin]);
	}

	int findMinInLevenstein(int start) {
		int min = levenstein[M][start + 1];
		int indexOfMin = start + 1;
		for (int i = start + 1; i < N + 1; i++) {
			if (levenstein[M][i] < min) {
//				System.out.println("found" + levenstein[M][i]);
				min = levenstein[M][i];
				indexOfMin = i;
			}
		}
		return indexOfMin;
	}

	int levensteinReverse(int start) {
//		System.out.println(start);
		int i = M;
		int k = start;
//		System.out.println("k = " + start + " i = " + i);
		while (i > 0) {
			if (k == 0) {
				break;
			}

//			int movedCode = findIndexMin(
//				levenstein[i - 1][k] + cD,
//				levenstein[i][k - 1] + cI,
//				levenstein[i - 1][k - 1] + (pattern.charAt(i - 1) == text.charAt(k - 1) ? 0 : cR)
//			);
			int movedCode = levensteinPath[i][k];
//			System.out.println("code is " + movedCode);
			if (movedCode == 1) {
				i--;
			} else if (movedCode == 2) {
				k--;
			} else {
				i--;
				k--;
			}
//			System.out.println(i + " " + k);
		}
//		System.out.println("__");
		if (i != 0) {
			return -1;
		}
//		System.out.println("leftmost " + k);
		return k;
	}

	int levensteinReversV2(int start) {
		int i = M;
		int k = start;
		return 1;

	}

	private int findMin(int a, int b, int c) {
		int min = a;

		if (b <= min) {
			min = b;
		}

		if (c <= min) {
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

	void printLevensteinPath() {
		for (int i = 0; i < M + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				System.out.print(levensteinPath[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("");
	}

}
