/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Thang Do
 */
public class WeightedGraph {

	static long[][] graph;

	public static void main(String[] args) throws IOException {
		//nějaké další values
		int c1 = 0;
		int c2 = 0;

		// počet hran

		int numOfBuildings = 0;
//		BufferedReader in = new BufferedReader(new FileReader("pub04.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String firstLine = in.readLine();

		// Get basic information c1 and c2 are criterias 
		StringTokenizer tokenizer = new StringTokenizer(firstLine);
		while (tokenizer.hasMoreTokens()) {
			numOfBuildings = Integer.valueOf(tokenizer.nextToken());
			c1 = Integer.valueOf(tokenizer.nextToken());
			c2 = Integer.valueOf(tokenizer.nextToken());
		}

		//weighted graph
		graph = new long[numOfBuildings][numOfBuildings];


		while ((firstLine = in.readLine()) != null) {
			tokenizer = new StringTokenizer(firstLine);

			// basic connection info 
			int ib1 = Integer.valueOf(tokenizer.nextToken());
			int ib2 = Integer.valueOf(tokenizer.nextToken());
			int weight = Integer.valueOf(tokenizer.nextToken());
			graph[ib1][ib2] = weight;
			graph[ib2][ib1] = weight;
		}
	}
}
