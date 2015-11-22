/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal04;

import java.util.ArrayList;

/**
 *
 * @author Thang Do
 */
public class Generator {

	int A, C, M, K, N;
	boolean[] eratoSito;
	ArrayList<Integer> primeTimesSubsets;
	ArrayList<Integer> primeNums;

	public Generator(int A, int C, int M, int K, int N) {
		this.A = A;
		this.C = C;
		this.M = M;
		this.K = K;
		this.N = N;
		eratoSito = new boolean[M + 1];

		primeNums = new ArrayList<>();
		primeTimesSubsets = new ArrayList<>();

		initEratoSito();
		calculatePrimeKsubsets();
	}

	void generate(int seed) {
		int[] vals = new int[N];
		vals[0] = seed;
		for (int i = 0; i < N - 1; i++) {
			vals[i + 1] = (A * vals[i] + C) % M;
		}
		for (int i = 0; i < vals.length; i++) {
			System.out.print(vals[i] + ",");
		}
	}

	void initEratoSito() {
		for (int i = 2; i < Math.sqrt(M); i++) {
			if (!eratoSito[i]) {
				for (int j = i; i * j < M; j++) {
					int index = i * j;
					eratoSito[index] = true;
				}
			}
		}
		for (int i = 2; i < M; i++) {
			if (!eratoSito[i]) {
				System.out.print(i + ",");
				primeNums.add(i);
			}
		}
//		System.out.println("");
//		System.out.println("Je tu" + count);
	}

	void calculatePrimeKsubsets() {
		int size = primeNums.size();
		int numRows = (int) Math.pow(2, size);
		boolean[][] bools = new boolean[numRows][size];

		//tj K - podmonožin
		int Ka = 2;
		int trueCounter = 0;

		int[] KaIndex = new int[Ka];

		for (int i = 0; i < bools.length; i++) {
			for (int j = 0; j < bools[i].length; j++) {
				int val = bools.length * j + i;
				int ret = (1 & (val >>> j));
				if (ret != 0) {
					if (trueCounter < Ka) {
						KaIndex[trueCounter] = j;
					}
					trueCounter++;
					bools[i][j] = true;
				}
//				System.out.print(bools[i][j] + "\t");
			}
			if (trueCounter == Ka) {
//				System.out.print("rádek " + i);
				int primeSubsetSum = 1;
				for (int j = 0; j < Ka; j++) {
					primeSubsetSum *= primeNums.get(KaIndex[j]);
				}
				primeTimesSubsets.add(primeSubsetSum);
			}
			trueCounter = 0;
//			System.out.println();
		}
	}

}
