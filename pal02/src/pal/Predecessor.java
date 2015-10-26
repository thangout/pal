/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

/**
 *
 * @author Thang
 */
public class Predecessor {

	int[] topologicalOrder;
	boolean[][] graph;
	boolean[][] graphReversed;
	boolean[][] predeccesor;
	boolean[][] successor;

	public Predecessor(boolean[][] graph, boolean[][] graphReversed, int[] topologicalOrder) {
		this.topologicalOrder = topologicalOrder;
		this.graph = graph;
		this.graphReversed = graphReversed;
		predeccesor = new boolean[graph.length][graph.length];
		successor = new boolean[graph.length][graph.length];
	}

	public void createPredecessor() {
		for (int i = 0; i < graph.length; i++) {
			int column = topologicalOrder[i];
			for (int j = 0; j < graph.length; j++) {
				//vede do uzlu hrana?
				if (graph[j][column]) {
					addPredOfNode(j, column);
					predeccesor[column][j] = true;
					successor[j][column] = true;
				}
			}
		}
	}

	private void addPredOfNode(int from, int to) {
		for (int i = 0; i < graph.length; i++) {
			if (predeccesor[from][i]) {
				predeccesor[to][i] = true;
				successor[i][to] = true;
			}
		}
	}
}
