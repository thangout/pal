/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

/**
 *
 * @author Thang
 */
public class TopologicalOrder {

	boolean[][] graph;
	int[] topoOrder;
	int pointerOrder;
	boolean[] permanentMark;
	boolean[] temporaryMark;

	public TopologicalOrder(boolean[][] graph) {
		this.graph = graph;
		permanentMark = new boolean[graph.length];
		temporaryMark = new boolean[graph.length];

		topoOrder = new int[graph.length];

		pointerOrder = graph.length - 1;
	}

	public void createTopologicalOrder() {
		for (int i = 0; i < graph.length; i++) {
			if (!permanentMark[i]) {
				dfs(i);
			}
		}
	}

	public void dfs(int index) {
		if (temporaryMark[index]) {
			return;
		}

		if (!permanentMark[index]) {
			temporaryMark[index] = true;
			for (int i = 0; i < graph.length; i++) {
				if (graph[index][i]) {
					dfs(i);
				}
			}
			permanentMark[index] = true;
			temporaryMark[index] = false;
			topoOrder[pointerOrder] = index;
			pointerOrder--;
		}
	}
}
