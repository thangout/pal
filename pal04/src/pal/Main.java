/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Thang Do
 */
public class Main {

	static int A, C, M, K, N;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("pub01.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String firstLine = in.readLine();

		StringTokenizer tokenizer = new StringTokenizer(firstLine);
//		while (tokenizer.hasMoreTokens()) {
//			numOfBuildings = Integer.valueOf(tokenizer.nextToken());
//		}
		A = Integer.valueOf(tokenizer.nextToken());
		C = Integer.valueOf(tokenizer.nextToken());
		M = Integer.valueOf(tokenizer.nextToken());
		K = Integer.valueOf(tokenizer.nextToken());
		N = Integer.valueOf(tokenizer.nextToken());

		Generator ge = new Generator(A, C, M, K, N);
		ge.findMostChallange();

//		long[] a = new long[300000000];
	}

}
