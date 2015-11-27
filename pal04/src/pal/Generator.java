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
		int primeNumsIsize;
		if (K > 1) {
			primeNumsIsize = (M / kas[K - 2])+1;
		} else {
			primeNumsIsize = (M);
		}
		eratoSito = new boolean[primeNumsIsize + 1];

//		primeTimesSubsets = new HashSet<>();
		primeTimesSub = new boolean[M];
		initEratoSito();
		makeRekSubsets(0, 0, 1L);
	}

	int[] generate(int seed) {
		int[] vals = new int[M];
		vals[0] = seed;
		for (int i = 0; i < M - 1; i++) {
			long tempVal = (A * (long) vals[i] + C);
			vals[i + 1] = (int) (tempVal % M);
		}
//		for (int i = 0; i < M; i++) {
//			System.out.println(vals[i] + ",");
//		}
		return vals;
	}

	int nextGen(long seed) {
		long tempVal = (A * seed + C);
		return (int) (tempVal % M);
	}

	void initEratoSito() {
		int primeNumsIsize;
		if (K > 1) {
			primeNumsIsize = (M / kas[K - 2]);
		} else {
			primeNumsIsize = (M);
		}

		primeNumsI = new int[primeNumsIsize];
		for (int i = 2; i < primeNumsIsize; i++) {
			if (!eratoSito[i]) {
				for (int j = i; i * j < M; j++) {
					int index = i * j;
					if (index > primeNumsIsize) {
						break;
					}
					eratoSito[index] = true;
				}
			}
			if (!eratoSito[i]) {
				primeNumsI[primeCount] = i;
//				System.out.println(i);
				primeCount++;
			}
		}
//		System.out.println(primeCount);
//		long sum = 1;
//		for (int i = 2; i < primeNumsIsize; i++) {
//			if (!eratoSito[i]) {
////				if (primeNumsI[primeCount] * i > M) {
//////					break;
////				}
//				
//				if (i > primeNumsIsize) {
//					break;
//				}
////				sum *= i;
//				primeCount++;
////				primeNums.add(i);
//			}
//		}
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
		int S = 0;

		//pocet prvočísel pro K
		int I = 0;

		int primeCounter = 0;

		int[] seeds = generate(0);
//		for (int i = 0; i < eratoSito.length; i++) {
//			eratoSito[i] = false;
//		}

		for (int i = 0; i < N; i++) {
			if (primeTimesSub[seeds[i]]) {
//				eratoSito[seeds[i]] = true;
				primeCounter++;
			}
		}
//			System.out.println(primeCounter);
		I = primeCounter;
		S = 0;

		int startPointer = 0;
//		int endPointer = seeds[N - 1];
		int endPointer = N - 1;

		startPointer++;
		endPointer++;
		while (startPointer != 0) {
//			System.out.println("+"+startPointer + " " + endPointer +  "=" + seeds[startPointer]);
			if (primeTimesSub[seeds[startPointer]]) {
				primeCounter--;
			}
			if (primeTimesSub[seeds[endPointer]]) {
				primeCounter++;
			}
			if (I < primeCounter) {
				//new max founded
				I = primeCounter;
//				S = startPointer;
				S = seeds[startPointer];
//				System.out.println("new seed " + S);
			}
			startPointer++;
			endPointer++;
			if (startPointer == M) {
				startPointer = 0;	
			}
			if (endPointer == M) {
				endPointer = 0;
			}
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
