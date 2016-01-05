/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Thang Do
 */
public class BreadthFirstSearch {

	//predpoklad je adjency matrix nebo jí podobna
	int[][] graph;

	public BreadthFirstSearch(int[][] graph) {
		this.graph = graph;
	}

	void bfs(int startVertex) {
		boolean[] visited = new boolean[graph.length];
		LinkedList<Integer> toVisit = new LinkedList<>();

		toVisit.add(startVertex);

		while (toVisit.size() != 0) {
			int v = (int) toVisit.poll();
			//když ještě není navštívený
			if (!visited[v]) {

				visited[v] = true;
				for (int i = 0; i < graph.length; i++) {
					//hledání sousedů, záleží pak na kritériu
					//tu je pro adjency list
					if (graph[v][i] > 0) {
						toVisit.add(i);
					}
				}

				//zpracování vrcholu sem 
				System.out.println(v);
			}
		}

	}
}
