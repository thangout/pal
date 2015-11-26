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
//	HashSet<Integer> primeTimesSubsets;
	int[] primeNumsI;
	boolean[] primeTimesSub;
	int primeCount = 0;
	int[] kas;

	public Generator(int A, int C, int M, int K, int N) {
		this.A = A;
		this.C = C;
		this.M = M;
		this.K = K;
		this.N = N;
		initKass();
		eratoSito = new boolean[M];

//		primeTimesSubsets = new HashSet<>();
		primeTimesSub = new boolean[M];
		initEratoSito();
		makeRekSubsets(0, 0, 1L);
	}

	int[] generate(int seed) {
		int[] vals = new int[N];
		vals[0] = seed;
		for (int i = 0; i < N - 1; i++) {
			long tempVal = (A * (long) vals[i] + C);
			vals[i + 1] = (int) (tempVal % M);
		}
//		for (int i = 0; i < 5; i++) {
//			System.out.println(vals[i] + ",");
//		}
		return vals;
	}

	int nextGen(long seed) {
		long tempVal = (A * seed + C);
		return (int) (tempVal % M);
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
		int primeNumsIsize = (M / kas[K - 2]);
		primeNumsI = new int[primeNumsIsize];

//		long sum = 1;
		for (int i = 2; i < M; i++) {
			if (!eratoSito[i]) {
//				if (primeNumsI[primeCount] * i > M) {
////					break;
//				}
				primeNumsI[primeCount] = i;
				if (i > primeNumsIsize) {
					break;
				}
//				sum *= i;
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
			primeTimesSub[(int) sum] = true;
//				primeTimesSubsets.add((int) sum);
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
//		for (int i = 0; i < eratoSito.length; i++) {
//			eratoSito[i] = false;
//		}

		for (int i = 0; i < N; i++) {
//			System.out.println("pa" +  i);
//			if (seeds[i] < primeTimesSub.length) {
			if (primeTimesSub[seeds[i]]) {
//				eratoSito[seeds[i]] = true;
				primeCounter++;
			}
//			}
//			if (primeTimesSubsets.contains((int) seeds[i])) {
//				eratoSito[seeds[i]] = true;
//				primeCounter++;
//			}
		}
		I = primeCounter;
		S = 0;

		int startPointer = 0;
		int endPointer = seeds[N - 1];

//		startPointer++;
//		endPointer++;
		startPointer = nextGen(startPointer);
		endPointer = nextGen(endPointer);
		while (startPointer != 0) {
			if (primeTimesSub[startPointer]) {
				primeCounter--;
			}
			if (primeTimesSub[endPointer]) {
				primeCounter++;
			}
//			if (!eratoSito[endPointer]) {
////				if (primeTimesSubsets.contains((int) endPointer)) {
////					eratoSito[endPointer] = true;
////					primeCounter++;
////				}
//				if (primeTimesSub[endPointer]) {
//					eratoSito[endPointer] = true;
//					primeCounter++;
//				}
//			} else {
//				primeCounter++;
//			}
//			System.out.println(primeCounter);
			if (I < primeCounter) {
				//new max founded
				I = primeCounter;
				S = startPointer;
//				System.out.println("new seed " + S);
			}
//			System.out.println("s"+startPointer);
			startPointer = nextGen(startPointer);
			endPointer = nextGen(endPointer);
//			startPointer = startPointer % M;
//			endPointer = endPointer % M;
		}

		System.out.println(S + " " + I);
	}

	void initKass() {
		kas = new int[8];
		kas[0] = 2;
		kas[1] = 6;
		kas[2] = 30;
		kas[3] = 210;
		kas[4] = 2310;
		kas[5] = 30030;
		kas[6] = 510510;
		kas[7] = 9699690;
//		kas[9] = 2156564410;	
	}
}
