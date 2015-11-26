/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author Thang Do
 */
public class Generator {

	int A, C, M, K, N;
	boolean[] eratoSito;
//	HashSet<Integer> primeTimesSubsets;
//	ArrayList<Integer> primeNums;
	int[] primeNumsI;
	int primeCount = 0;
	boolean[] primeTimesSub;

	public Generator(int A, int C, int M, int K, int N) {
		this.A = A;
		this.C = C;
		this.M = M;
		this.K = K;
		this.N = N;
		eratoSito = new boolean[M + 1];

//		primeNums = new ArrayList<>();
//		primeTimesSubsets = new HashSet<>();
		primeTimesSub = new boolean[M];
		initEratoSito();
//		calculatePrimeKsubsets(K);
//		makePrimeSubsets(K);
//		initRekSubset();
		makeRekSubsets(0, 0, 1L);
	}

	long[] generate(int seed) {
		long[] vals = new long[M];
//		boolean[] isIn = new boolean[M];
		vals[0] = seed;
//		isIn[seed] = true;
		for (int i = 0; i < M - 1; i++) {
			vals[i + 1] = (A * vals[i] + C) % M;
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
		int primeNumsIPointer = 0;

		for (int i = 2; i < (M / K); i++) {
			if (!eratoSito[i]) {
				primeNumsI[primeNumsIPointer] = i;
				primeCount++;
				primeNumsIPointer++;
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
				primeTimesSub[(int) sum] = true;
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

		long[] seeds = generate(0);
//		boolean[] isInPrimeSubset = new boolean[M];
		for (int i = 0; i < eratoSito.length; i++) {
			eratoSito[i] = false;	
		}
		for (int i = 0; i < N; i++) {
//			System.out.println("pa" +  i);
			if (seeds[i] < primeTimesSub.length && seeds[i] > 0) {
				if (primeTimesSub[(int) seeds[i]]) {
					eratoSito[i] = true;
					primeCounter++;
				}
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
//			System.out.println("S:" + startPointer);
//			System.out.println("F:" + endPointer);
//			System.out.println("_");
//			if (primeTimesSubsets.contains(seeds[startPointer])) {
//				primeCounter--;
//			}
			if (eratoSito[startPointer]) {
				primeCounter--;
			}
			if (!eratoSito[endPointer]) {
				if (seeds[endPointer] > 0) {
					if (primeTimesSub[ (int) seeds[endPointer]]) {
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
