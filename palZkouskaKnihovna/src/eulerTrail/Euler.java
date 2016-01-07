/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eulerTrail;

import java.util.Stack;

/**
 *
 * @author Thang Do
 */
public class Euler {

	int[][] graph;
	Stack stack;

	public Euler(int[][] graph) {
		this.graph = graph;
		stack = new Stack();
	}

	public void eulerTrail(int vertex) {
		for (int j = 0; j < graph.length; j++) {
			if (graph[vertex][j] > 0) {
				//remove edge
				graph[vertex][j] = 0;
				eulerTrail(j);
				stack.push(new Edge(vertex, j));
			}
		}
	}

	//vratí true pokud je v grafu Eulerův tah
	boolean testEuler() {
		int oddDegreeCount = 0;
		for (int i = 0; i < graph.length; i++) {
			int degreeCount = 0;
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j] > 0) {
					degreeCount++;
				}
			}
			if (degreeCount % 2 == 1) {
				oddDegreeCount++;
			}
		}

		// 0 => tah začíná a končí ve stejném bodě
		// 2 => tah začíná a končí na různých místě
		if (oddDegreeCount == 0 || oddDegreeCount == 2) {
			return true;
		}
		return false;
	}
}
