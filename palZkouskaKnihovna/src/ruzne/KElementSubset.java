/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruzne;

/**
 *
 * @author Thang Do
 */
public class KElementSubset {

	public static void main(String[] args) {
		int[] a = {1, 2, 3};
		int n = a.length;
		int k = 2;
//		int[] res = kSubsetLexSuccessor(a, n, k);

//		int[] res2 = kSubsetLexUnrank(1, n, k);
		int res3 = kSubsetLexRank(a, n, k);
		System.out.println(res3);
	}

	/**
	 *
	 * @param subsetT k-prvková podmnožina
	 * @param n velikost rozash
	 * @param k velikost k-prvkové podmnožiny
	 * @return Vrátí lexikograficky následníka k-prvkové podmnožiny
	 * subsetArrayT
	 */
	public static int[] kSubsetLexSuccessor(int[] subsetT, int n, int k) {
		int[] U = new int[subsetT.length];
		int i = k;
		while (i >= 1) {
			i--;
			if (subsetT[i] == n - k + i) {
				break;
			}
			for (int j = i; j < k; j++) {
				U[j] = subsetT[i] + 1 + j - i;
			}
		}
		return U;
	}

	/**
	 *
	 * @param subsetT
	 * @param n
	 * @param k
	 * @return
	 */
	public static int kSubsetLexRank(int[] subsetT, int n, int k) {
		int rank = 0;
		subsetT[0] = 0;
		for (int i = 1; i < k; i++) {
			if (subsetT[i - 1] + 1 <= subsetT[i] - 1) {
				for (int j = subsetT[i - 1] + 1; j < subsetT[i] - 1; j++) {
					rank = rank + ncr(n - j, k - i);
				}
			}
		}
		return rank;
	}

	/**
	 *
	 * @param rank
	 * @param n
	 * @param k
	 * @return vrátí lexikografický k-prvkovou podmnožinu z rank
	 */
	public static int[] kSubsetLexUnrank(int rank, int n, int k) {
		int x = 1;
		int[] T = new int[n];
		for (int i = 1; i < k; i++) {
			while (ncr(n - x, k - i) <= rank) {
				rank = rank - ncr(n - x, k - i);
				x++;
			}
			T[i] = x;
			x++;
		}
		return T;
	}

	/**
	 * Klasický faktorial
	 *
	 * @param i
	 * @return
	 */
	static int fac(int i) {
		if (i == 0) {
			return 1;
		}

		if (i == 1) {
			return 1;
		}
		return i * fac(--i);
	}

	/**
	 *
	 * @param n
	 * @param k
	 * @return N nad K
	 */
	static int ncr(int n, int k) {
		return fac(n) / (fac(n - k) * fac(k));

	}

	static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
	}
}
