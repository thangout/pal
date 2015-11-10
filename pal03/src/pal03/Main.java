/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author Thang Do
 */
public class Main {

	static boolean[][] graphA;
	static boolean[][] graphB;
	static long[] weight;

	static int[] fromEdge;
	static int[] toEdge;
	static int edgePointer;

	public static void main(String[] args) throws IOException {
		// TODO code application logic here
		int numOfBuildings = 0;
		BufferedReader in = new BufferedReader(new FileReader("pub04.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String firstLine = in.readLine();

		StringTokenizer tokenizer = new StringTokenizer(firstLine);
		while (tokenizer.hasMoreTokens()) {
			numOfBuildings = Integer.valueOf(tokenizer.nextToken());
		}

		graphA = new boolean[numOfBuildings][numOfBuildings];
		graphB = new boolean[numOfBuildings + 1][numOfBuildings + 1];
		weight = new long[numOfBuildings];

		for (int i = 0; i < numOfBuildings - 1; i++) {
			firstLine = in.readLine();
			tokenizer = new StringTokenizer(firstLine);
			int ib1 = Integer.valueOf(tokenizer.nextToken());
			int ib2 = Integer.valueOf(tokenizer.nextToken());
			graphA[ib1][ib2] = true;
			graphA[ib2][ib1] = true;
//			fromEdge[edgePointer] = ib1;
//			toEdge[edgePointer] = ib2;
//			edgePointer++;
		}

		for (int i = 0; i < numOfBuildings; i++) {
			firstLine = in.readLine();
			tokenizer = new StringTokenizer(firstLine);
			int ib1 = Integer.valueOf(tokenizer.nextToken());
			int ib2 = Integer.valueOf(tokenizer.nextToken());
			graphB[ib1][ib2] = true;
			graphB[ib2][ib1] = true;
//			fromEdge[edgePointer] = ib1;
//			toEdge[edgePointer] = ib2;
//			edgePointer++;
		}
		in.close();

//		Certificate cs = new Certificate(graphA, graphB);
		TreeCert tc = new TreeCert(graphA, graphB);
		tc.computeCert(graphA);

//		ArrayList<String> asd = new ArrayList<>();
//		asd.add("0011");
//		asd.add("001011");
//		Collections.sort(asd);
//		System.out.println(asd.get(0));
	}
}
