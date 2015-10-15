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
public class MinST {

	//pole booleanu jestli jestli je vert v spt nebo neni
	boolean[] mstSet;

	//pole rodicu - ulozeni indexů rodiču
	int[] parrents;

	//pole vah k jednotlivým uzlům
	long[] keys;

	long[][] graph;

	public MinST(long[][] graph) {
		mstSet = new boolean[graph.length];
		parrents = new int[graph.length];
		keys = new long[graph.length];
		this.graph = graph;
		prim();
	}

	public void prim() {
		//incializace zakladnich hodnot
		for (int i = 0; i < graph.length; i++) {
			keys[i] = Long.MAX_VALUE;
			mstSet[i] = false;
		}

		//inicializace prvniho vertu v mst 
		keys[0] = 0;
		parrents[0] = -1;

		for (int i = 0; i < graph.length - 1; i++) {

			//index připojovacího vertu
			int u = findMinKey(keys, mstSet);

			//je zarazen v MST
			mstSet[u] = true;

			//pridavam do keys další uzly které sousedí s vertem u
			// a které nejsou už v MST
			// a které s u sousedí tedy mají nenulovou hodnotu
			for (int j = 0; j < graph.length; j++) {
				if (graph[u][j] > 0 && graph[u][j] < keys[j] && mstSet[j] == false) {
					parrents[j] = u;
					keys[j] = graph[u][j];
				}

			}
		}

	}

	//metoda minkey, najde vhodný vert pro připojeni, vrati jeho index
	private int findMinKey(long[] keys, boolean[] mstSet) {
		long min = Long.MAX_VALUE;
		int min_index = 0;

		for (int i = 0; i < graph.length; i++) {
			if (mstSet[i] == false && keys[i] < min) {
				min = keys[i];
				min_index = i;
			}
		}
		return min_index;
	}

	public long getMSTWeight() {
		long weight = 0;
		for (int i = 1; i < parrents.length; i++) {
			weight += graph[i][parrents[i]];
		}
		return weight;
	}
}
