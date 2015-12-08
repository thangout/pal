/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal05;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Thang Do
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		int S, A, F, P, N, L;
		BufferedReader in = new BufferedReader(new FileReader("pub01.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String firstLine = in.readLine();

		StringTokenizer tokenizer = new StringTokenizer(firstLine);
//		while (tokenizer.hasMoreTokens()) {
//			numOfBuildings = Integer.valueOf(tokenizer.nextToken());
//		}
		S = Integer.valueOf(tokenizer.nextToken());
		A = Integer.valueOf(tokenizer.nextToken());
		F = Integer.valueOf(tokenizer.nextToken());
		P = Integer.valueOf(tokenizer.nextToken());
		N = Integer.valueOf(tokenizer.nextToken());

		//length of example
		L = Integer.valueOf(tokenizer.nextToken());

		int[][] states = new int[S][A];
		for (int i = 0; i < S; i++) {
			firstLine = in.readLine();
			tokenizer = new StringTokenizer(firstLine);
			int row = Integer.valueOf(tokenizer.nextToken());
			for (int j = 0; j < A; j++) {
				int column = Integer.valueOf(tokenizer.nextToken());
				states[row][j] = column;  
			}
		}

		String[] examples = new String[P];
		for (int i = 0; i < P; i++) {
			firstLine = in.readLine();
			examples[i] = firstLine;
		}

		Automat au = new Automat(S, A, F, P, N, L, states, examples);

	}

}
