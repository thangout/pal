/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thang Do
 */
public class Main {

	static long[][] graph;

	public static void main(String[] args) throws IOException {
		int c1 = 0;
		int c2 = 0;
//		System.out.println("135\n0 1 28\n1 5 26\n10 14 25");
		BufferedReader in = new BufferedReader(new FileReader("pub02.in"));
//			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String firstLine = in.readLine();

		// Get basic information c1 and c2 are criterias 
		StringTokenizer tokenizer = new StringTokenizer(firstLine);
		int numOfBuildings = Integer.valueOf(tokenizer.nextToken());
		int numOfConnections = Integer.valueOf(tokenizer.nextToken());
		c1 = Integer.valueOf(tokenizer.nextToken());
		c2 = Integer.valueOf(tokenizer.nextToken());

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

		MinST spt = new MinST(graph);

		System.out.println(spt.getMSTWeight());
		for (int i = 1; i < spt.parrents.length; i++) {
			System.out.println(spt.parrents[i] + " " + i + " " + graph[i][spt.parrents[i]]);
			
		}
	}

}
