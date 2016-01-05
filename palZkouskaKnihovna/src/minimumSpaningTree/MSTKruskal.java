/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimumSpaningTree;

import java.util.PriorityQueue;

/**
 *
 * @author Thang Do
 */
public class MSTKruskal {

	//graf s váhami
	int[][] graph;

	//pointry na rodiče stromu tedy kořene;
	int[] set;

	PriorityQueue<Edge> pq;

	Edge[] A;

	public MSTKruskal(int[][] graph) {
		this.graph = graph;
		set = new int[graph.length];
		A = new Edge[graph.length-1];
		init();
	}

	void init() {
		for (int i = 0; i < graph.length; i++) {
			makeSet(i, i);
		}

		EdgeWeightComparator comparator = new EdgeWeightComparator();
		pq = new PriorityQueue<>(20, comparator);

		//tvorba edgu
		for (int i = 0; i < graph.length; i++) {
			for (int j = i; j < graph.length; j++) {
				if (graph[i][j] > 0) {
					pq.add(new Edge(i, j, graph[i][j]));
				}
			}
		}
	}

	public void kruskal() {
		int edgeInACounter = 0;
		while (pq.size() > 0) {
			Edge e = pq.poll();
			System.out.println(findSet(e.u) + " : " + findSet(e.v));
			if (findSet(e.u) != findSet(e.v)) {
				A[edgeInACounter] = e;
				union(e.u, e.v);
				edgeInACounter++;
			}
		}
	}

	void makeSet(int indexVert, int root) {
		set[indexVert] = root;
	}

	int findSet(int index) {
		return set[index];
	}

	void union(int u, int v) {
		int oldSetU = set[u];
		for (int i = 0; i < graph.length; i++) {
			if (set[i] == oldSetU) {
				set[i] = set[v];
			}
		}
	}
}
