/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfs;

import java.util.Stack;

/**
 *
 * @author Thang Do
 */
public class DepthFirstSearch {

	//predpoklad je adjency matrix nebo jí podobna
	int[][] graph;

	// pro dfs rekurzivně
	boolean[] permanentMark;
	boolean[] temporaryMark;
	int[] predecessor;

	public DepthFirstSearch(int[][] graph) {
		this.graph = graph;
		temporaryMark = new boolean[graph.length];
		permanentMark = new boolean[graph.length];
		predecessor = new int[graph.length];
	}

	/********************************************************
	  	 DFS pomocí stacku	
	*********************************************************/
	void dfs(int startVertex) {
		boolean[] visited = new boolean[graph.length];
		Stack toVisit = new Stack();

		toVisit.push(startVertex);

		while (toVisit.size() != 0) {
			int v = (int) toVisit.pop();
			//když ještě není navštívený
			if (!visited[v]) {
				visited[v] = true;
				for (int i = 0; i < graph.length; i++) {
					//hledání sousedů, záleží pak na kritériu
					//tu je pro adjency list
					if (graph[v][i] > 0) {
						toVisit.push(i);
					}
				}

				//zpracování vrcholu sem 
				System.out.println(v);
			}
		}
	}

	/********************************************************
	  	 DFS rekursivně	
	*********************************************************/
	void startDfsRecursive() {
		for (int i = 0; i < graph.length; i++) {
			if (!permanentMark[i]) {
				dfsRecursive(i);
			}
		}
	}

	void dfsRecursive(int startVertex) {
		//temporaryMark - když vstoupím do Vertexu
		//permamenentMark - uzavření Vertexu už se k něm nevracím

		if (!permanentMark[startVertex]) {
			//sem zpracování vrcholu
			temporaryMark[startVertex] = true;
			for (int i = 0; i < graph.length; i++) {
				if (graph[startVertex][i] > 0 && !temporaryMark[i]) {
					predecessor[i] = startVertex;
					dfsRecursive(i);
				}
			}
			temporaryMark[startVertex] = false;
			permanentMark[startVertex] = true;
			
			//**** sem zpracování vrcholu *****
			System.out.println(startVertex);
		}
	}
}
