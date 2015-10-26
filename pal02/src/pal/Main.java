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

	/**
	 * @param args the command line arguments
	 */
	static boolean[][] graph;
	static boolean[][] graphReversed;
	static long[] weight;

	public static void main(String[] args) throws IOException {
		int numOfBuildings = 0;
		int numOfConnections = 0;
//		BufferedReader in = new BufferedReader(new FileReader("pub03.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String firstLine = in.readLine();

		StringTokenizer tokenizer = new StringTokenizer(firstLine);
		while (tokenizer.hasMoreTokens()) {
			numOfBuildings = Integer.valueOf(tokenizer.nextToken());
			numOfConnections = Integer.valueOf(tokenizer.nextToken());
		}

		graph = new boolean[numOfBuildings][numOfBuildings];
//		graphReversed = new boolean[numOfBuildings][numOfBuildings];
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
			graph[ib1][ib2] = true;
//			graphReversed[ib2][ib1] = true;
		}
		in.close();

		TopologicalOrder to = new TopologicalOrder(graph);
		to.createTopologicalOrder();

		Predecessor pred = new Predecessor(graph, graphReversed, to.topoOrder);
		pred.createPredecessor();

		ReverseEdge reverse = new ReverseEdge(graph, pred.predeccesor, pred.successor, weight);
		reverse.findEdge();
	}

}
