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
	HashSet<Integer> primeTimesSubsets;
	ArrayList<Integer> primeNums;
	int[] primeNumsI;
	int primeCount = 0;

	public Generator(int A, int C, int M, int K, int N) {
		this.A = A;
		this.C = C;
		this.M = M;
		this.K = K;
		this.N = N;
		eratoSito = new boolean[M + 1];

		primeNums = new ArrayList<>();
		primeTimesSubsets = new HashSet<>();

		initEratoSito();
//		calculatePrimeKsubsets(K);
//		makePrimeSubsets(K);
		initRekSubset();
	}

	int[] generate(int seed) {
		int[] vals = new int[M];
		boolean[] isIn = new boolean[M];
		vals[0] = seed;
		isIn[seed] = true;
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

		for (int i = 2; i < M / K; i++) {
			if (!eratoSito[i]) {
				primeNumsI[primeNumsIPointer] = i;
				primeCount++;
				primeNumsIPointer++;
//				primeNums.add(i);
			}
		}
	}

	void initRekSubset() {
		for (int i = 0; i < primeCount; i++) {
			makeRekSubsets(i, 0, 1, false);
		}

	}

	void makeRekSubsets(int prevIndex, int height, int sum, boolean stopRek) {
		sum *= primeNumsI[prevIndex];
		if (sum > M || sum < 0) {
			stopRek = true;
			return;
		}
//		System.out.print(prevIndex);
		if (height == K - 1 || stopRek) {
			if (sum <= M) {
//				System.out.println(sum);
				primeTimesSubsets.add(sum);
			}
//			System.out.println("Sum si " + sum);
			return;
		}
		height++;
		prevIndex++;
		for (int i = prevIndex; i < primeCount; i++) {
//			System.out.print(i);
			makeRekSubsets(i, height, sum, stopRek);
		}
//		System.out.println("_");
	}

	void findMostChallange() {
		//best Seed
		int S = 0;

		//pocet prvočísel pro K
		int I = 0;

		int primeCounter = 0;

		int[] seeds = generate(0);
		boolean[] isInPrimeSubset = new boolean[M];
		for (int i = 0; i < N; i++) {
//			System.out.println("pa" +  i);
			if (primeTimesSubsets.contains(seeds[i])) {
				isInPrimeSubset[i] = true;
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
//			System.out.println("S:" + startPointer);
//			System.out.println("F:" + endPointer);
//			System.out.println("_");
//			if (primeTimesSubsets.contains(seeds[startPointer])) {
//				primeCounter--;
//			}
			if (isInPrimeSubset[startPointer]) {
				primeCounter--;
			}
			if (!isInPrimeSubset[endPointer]) {
				if (primeTimesSubsets.contains(seeds[endPointer])) {
					isInPrimeSubset[endPointer] = true;
					primeCounter++;
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
