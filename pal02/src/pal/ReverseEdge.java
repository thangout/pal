/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

/**
 *
 * @author Thang Do
 */
public class ReverseEdge {

	boolean[][] graph;
	boolean[][] predeccesor;
	boolean[][] successor;
	long[] weight;
	long[] bestEdge;

	public ReverseEdge(boolean[][] graph, boolean[][] predeccesor, boolean[][] successor, long[] weight) {
		this.graph = graph;
		this.predeccesor = predeccesor;
		this.successor = successor;
		this.weight = weight;
		this.bestEdge = new long[3];
	}

	public void findEdge() {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				//je tam hrana mezi nodama?
				if (graph[i][j]) {
					//spocitej cenu pruniku succ a pred
					//a najdi max
					long componentWeight = intersect(i, j);
					if (checkMax(componentWeight)) {
						bestEdge[0] = i;
						bestEdge[1] = j;
						bestEdge[2] = componentWeight;
					}
				}
			}
		}
		System.out.println(bestEdge[0] + " " + bestEdge[1] + " " + bestEdge[2]);
	}

	private long intersect(int x, int y) {
		long componentWeight = 0;
		for (int i = 0; i < graph.length; i++) {
			if (successor[x][i] && predeccesor[y][i]) {
				componentWeight += weight[i];
			}
		}
		componentWeight += weight[x] + weight[y];
		return componentWeight;
	}

	private boolean checkMax(long weight) {
		if (bestEdge[2] < weight) {
			return true;
		}
		return false;
	}
}
