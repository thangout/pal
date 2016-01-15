/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strongConnectedComponents;

import dfs.*;
import java.util.Stack;

/**
 *
 * @author Thang Do
 */
public class DFSKosaraju {

	//predpoklad je adjency matrix nebo jí podobna
	int[][] graph;

	// pro dfs rekurzivně
	boolean[] permanentMark;
	boolean[] temporaryMark;

	//Stack pro Kosaraju
	Stack stack;

	//ukládá si indexy prošlých komponent
	boolean[][] visited;
	int componentCounter = 0;

	public DFSKosaraju(int[][] graph) {
		this.graph = graph;
		temporaryMark = new boolean[graph.length];
		permanentMark = new boolean[graph.length];
		stack = new Stack();
		visited = new boolean[graph.length][graph.length];
	}

	/**
	 * ******************************************************
	 * DFS rekursivně
	 * *******************************************************
	 */
	void startDfsRecursive() {
		for (int i = 0; i < graph.length; i++) {
			if (!permanentMark[i]) {
				dfsRecursivePushStack(i);
			}
		}
	}

	void dfsRecursivePushStack(int startVertex) {
		//temporaryMark - když vstoupím do Vertexu
		//permamenentMark - uzavření Vertexu už se k něm nevracím

		if (!permanentMark[startVertex]) {
			//sem zpracování vrcholu
			temporaryMark[startVertex] = true;
			for (int i = 0; i < graph.length; i++) {
				if (graph[startVertex][i] > 0 && !temporaryMark[i]) {
					dfsRecursivePushStack(i);
				}
			}
			temporaryMark[startVertex] = false;
			permanentMark[startVertex] = true;

			//**** sem zpracování vrcholu *****
			stack.push(startVertex);
		}
	}

	void startDfsRecursiveComponent() {
		while (!stack.empty()) {
			int v = (int) stack.pop();
			if (!permanentMark[v]) {
				dfsRecursiveComponent(v);
				componentCounter++;
			}
		}
	}

	void dfsRecursiveComponent(int startVertex) {
		if (!permanentMark[startVertex]) {
			//sem zpracování vrcholu
			temporaryMark[startVertex] = true;
			for (int i = 0; i < graph.length; i++) {
				//pozor tady se prochází obráceně index ->
				// jako kdyby byla matice transponovaná
				if (graph[i][startVertex] > 0 && !temporaryMark[i]) {
					dfsRecursiveComponent(i);
				}
			}
			temporaryMark[startVertex] = false;

			//**** sem zpracování vrcholu *****
			permanentMark[startVertex] = true;
			visited[componentCounter][startVertex] = true;
		}
	}

	void clearPermanentMark() {
		for (int i = 0; i < permanentMark.length; i++) {
			permanentMark[i] = false;
		}
	}

	void printComponents(int rows) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < graph.length; j++) {
				if (visited[i][j]) {
					System.out.print(j + ", ");
				}
			}
			System.out.println();
		}

	}
}
