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
public class Pal {

	/**
	 * @param args the command line arguments
	 */
	static long[][] graph;
	static long[] weight;

	public static void main(String[] args) throws IOException {
		int numOfBuildings = 0;
		int numOfConnections = 0;
		BufferedReader in = new BufferedReader(new FileReader("pub01.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String firstLine = in.readLine();

		StringTokenizer tokenizer = new StringTokenizer(firstLine);
		while (tokenizer.hasMoreTokens()) {
			numOfBuildings = Integer.valueOf(tokenizer.nextToken());
			numOfConnections = Integer.valueOf(tokenizer.nextToken());
		}

		graph = new long[numOfBuildings][numOfBuildings];
		weight = new long[numOfBuildings];
		for (int i = 0; i < numOfBuildings; i++) {
			firstLine = in.readLine();
			tokenizer = new StringTokenizer(firstLine);
			weight[i] = Integer.valueOf(tokenizer.nextToken());

		}
		for (int i = 0; i < numOfConnections; i++) {
			firstLine = in.readLine();
			tokenizer = new StringTokenizer(firstLine);
			int ib1 = Integer.valueOf(tokenizer.nextToken());
			int ib2 = Integer.valueOf(tokenizer.nextToken());
			graph[ib1][ib2] = 1;
		}
		in.close();
	}

}
