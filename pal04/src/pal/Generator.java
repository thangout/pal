/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.util.HashSet;

/**
 *
 * @author Thang Do
 */
public class Generator {

	int A, C, M, K, N;
	boolean[] eratoSito;
	HashSet<Integer> primeTimesSubsets;
//	ArrayList<Integer> primeNums;
	int[] primeNumsI;
//	boolean[] primeTimesSub;
	int primeCount = 0;

	public Generator(int A, int C, int M, int K, int N) {
		this.A = A;
		this.C = C;
		this.M = M;
		this.K = K;
		this.N = N;
		eratoSito = new boolean[M];

//		primeNums = new ArrayList<>();
		primeTimesSubsets = new HashSet<>();
//		primeTimesSub = new boolean[M];
		initEratoSito();
//		calculatePrimeKsubsets(K);
//		makePrimeSubsets(K);
//		initRekSubset();
		makeRekSubsets(0, 0, 1L);
	}

	int[] generate(int seed) {
		int[] vals = new int[M];
//		boolean[] isIn = new boolean[M];
		vals[0] = seed;
//		isIn[seed] = true;
		for (int i = 0; i < M - 1; i++) {
			long tempVal = (A * (long) vals[i] + C);
//			System.out.println(tempVal);
			vals[i + 1] = (int) (tempVal % M);
//			isIn[vals[i + 1]] = true;
		}
//		for (int i = 0; i < vals.length; i++) {
//			System.out.println(vals[i] + ",");
//		}
		return vals;
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
		primeNumsI = new int[M / K];

		for (int i = 2; i < (M / K); i++) {
			if (!eratoSito[i]) {
				primeNumsI[primeCount] = i;
				primeCount++;
//				primeNums.add(i);
			}
		}
	}

	void makeRekSubsets(int prevIndex, int height, long sum) {
//		if (sum > M) {
//			return;
//		}
		if (height == K) {
			if (sum <= M) {
//				primeTimesSub[(int) sum] = true;
				primeTimesSubsets.add((int) sum);
			}
			return;
		} else {
			for (int i = prevIndex; i < primeCount; i++) {
				long sumTemp = sum * primeNumsI[i];
				if (sumTemp > M) {
//					System.out.println("*" + primeNumsI[i] + " going back " + sum * primeNumsI[i] + " > " + M);
					return;
				} else {
//					prevIndex++;
					int newI = i + 1;
					int newHeight = height + 1;
					makeRekSubsets(newI, newHeight, sumTemp);
				}
			}
		}
	}

	void findMostChallange() {
		//best Seed
		long S = 0;

		//pocet prvočísel pro K
		int I = 0;

		int primeCounter = 0;

		int[] seeds = generate(0);
//		boolean[] isInPrimeSubset = new boolean[M];
		for (int i = 0; i < eratoSito.length; i++) {
			eratoSito[i] = false;
		}

		for (int i = 0; i < N; i++) {
//			System.out.println("pa" +  i);
//			if (seeds[i] < primeTimesSub.length) {
//				if (primeTimesSub[(int) seeds[i]]) {
//					eratoSito[i] = true;
//					primeCounter++;
//				}
//			}
			if (primeTimesSubsets.contains((int) seeds[i])) {
				eratoSito[i] = true;
				primeCounter++;
			}
		}
//		System.out.println("kuki" + primeCounter);
		I = primeCounter;
		S = 0;

		int startPointer = 0;
		int endPointer = startPointer + N - 1;

		startPointer++;
		endPointer++;
		while (startPointer != 0) {
			if (eratoSito[startPointer]) {
				primeCounter--;
			}
			if (!eratoSito[endPointer]) {
				if (seeds[endPointer] > 0) {
					if (primeTimesSubsets.contains((int) seeds[endPointer])) {
						eratoSito[endPointer] = true;
						primeCounter++;
					}
				}
			} else {
				primeCounter++;
			}
//			System.out.println(primeCounter);
			if (I < primeCounter) {
				//new max founded
				I = primeCounter;
				S = seeds[startPointer];
//				System.out.println("new seed " + S);
			}
			startPointer++;
			endPointer++;
			startPointer = startPointer % M;
			endPointer = endPointer % M;
		}

		System.out.println(S + " " + I);
	}
}
