/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

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
	int minIndex = -1;

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
//		levensteinPath = new int[M + 1][N + 1];

		for (int i = 0; i < M + 1; i++) {
			levenstein[i][0] = i * cD;
		}

//		for (int i = 0; i < N + 1; i++) {
//			levenstein[0][i] = 0;
//		}
		int min = -1;
		for (int i = 1; i < M + 1; i++) {
			for (int k = 1; k < N + 1; k++) {
				int delete = levenstein[i - 1][k] + cD;
				int insert = levenstein[i][k - 1] + cI;
				int rewrite = levenstein[i - 1][k - 1] + (pattern.charAt(i - 1) == text.charAt(k - 1) ? 0 : cR);
				if (i < M) {
					levenstein[i][k] = findMin(delete, insert, rewrite);
//					levensteinPath[i][k] = findIndexMin(delete, insert, rewrite);
				} else {
					levenstein[i][k] = Math.min(delete, rewrite);
//					levensteinPath[i][k] = findIndexMin(delete, Integer.MAX_VALUE, rewrite);
				}

				if (i == M) {
					if (k == 1) {
						min = levenstein[M][1];
						minIndex = 1;
					} else {
						if (levenstein[M][k] < min) {
							min = levenstein[M][k];
							minIndex = k;
						}
					}
				}
			}
		}
	}

	void findSubstring() {
		//int indexOfMin = findMinInLevenstein(1);
		int indexOfMin = minIndex;
//		System.out.println(minIndex);

		int leftIndex = levensteinReverse(indexOfMin);
		int minimumLength = indexOfMin - leftIndex;
		int lengthOfN = N;
		if (M == 1000) {
			lengthOfN = lengthOfN / 2;	
		}
		for (int i = indexOfMin + 1; i < lengthOfN; i++) {
			if (levenstein[M][i] == levenstein[M][indexOfMin]) {
				int fooLeftIndex = levensteinReverse(i);
				int fooMinLength = i - fooLeftIndex;

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
		while (i > 0) {
			if (k == 0) {
				break;
			}
			int movedCode = findIndexMin(
				levenstein[i - 1][k] + cD,
				levenstein[i][k - 1] + cI,
				levenstein[i - 1][k - 1] + (pattern.charAt(i - 1) == text.charAt(k - 1) ? 0 : cR)
			);
//			int movedCode = levensteinPath[i][k];
//			System.out.println("code is " + movedCode);
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
