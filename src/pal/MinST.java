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
	//graf
	long[][] graph;
	boolean[][] firstComponent;
	boolean[][] inverseComponent;
	int[] componentParent;
	boolean[][] officialMST;
	//hrany které lze vyměnit
	int[] results;

	public MinST(long[][] graph) {
		mstSet = new boolean[graph.length];
		parrents = new int[graph.length];
		keys = new long[graph.length];
		this.graph = graph;
		this.firstComponent = new boolean[graph.length][graph.length];
		this.componentParent = new int[graph.length];
		officialMST = new boolean[graph.length][graph.length];
		results = new int[graph.length];
		inverseComponent = new boolean[graph.length][graph.length];
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

			// pridavam do keys další uzly které sousedí s vertem u
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
			if (parrents[i] >= 0) {
				weight += graph[i][parrents[i]];
				officialMST[i][parrents[i]] = true;
				officialMST[parrents[i]][i] = true;
				inverseComponent[i][parrents[i]] = true;
				inverseComponent[parrents[i]][i] = true;
			}
		}
		return weight;
	}

	public int findMinEdge() {
		long minWeight = graph[1][parrents[1]];
		int minIndex = 1;
		for (int i = 1; i < parrents.length; i++) {
			if (minWeight > graph[i][parrents[i]]) {
				minWeight = graph[i][parrents[i]];
				minIndex = i;
			}
		}
		return minIndex;
	}

	public void walkDFS() {
	}

	public void dfs(int index) {
		for (int i = 0; i < parrents.length; i++) {
			//když je te to jeho soused a je v mst
			//pak ho přidej do komponenty
			//pokud v komponentě už není
			//[radek][sloupec]
//			int startI = 0;
//			if (parrents[i] == -1) {
//					
//			}
//			System.out.println(isOfficialMST(index, i));
			if (isOfficialMST(index, i) && firstComponent[index][i] == false && firstComponent[i][index] == false //				&& graph[index][parrents[i]] >= 0) {
				) {
				firstComponent[index][i] = true;
				firstComponent[i][index] = true;
				inverseComponent[index][i] = false;
				inverseComponent[i][index] = false;
				dfs(i);
			}
//			if (parrents[i] >= 0) {
//				if (graph[index][i] > 0 && checkMSTEdge(graph[index][i]) && firstComponent[i] == false) {
//					firstComponent[i] = true;
//					componentParent[i] = i;
////				System.out.println("soused-" + i + " " + index);
//					dfs(i);
//				}
//			}

		}
	}

	int getComponentSize() {
		return firstComponent.length;
	}

	void removeCheapestEdge() {
		int i = findMinEdge();
		officialMST[i][parrents[i]] = false;
		officialMST[parrents[i]][i] = false;
		inverseComponent[i][parrents[i]] = false;
		inverseComponent[parrents[i]][i] = false;
//		graph[i][parrents[i]] = -4;
//		graph[parrents[i]][i] = -4;
//		mstSet[i] = false;
		parrents[0] = 0;
	}

	void findPotentialEdges(long c1, long c2) {
		for (int i = 0; i < graph.length; i++) {
			for (int j = i; j < graph.length; j++) {
				if (graph[i][j] >= c1 && graph[i][j] <= c2) {
					if (!isNotInComponent(i, j)) {
						results[i] = j;
//						System.out.println("aloh");
					}
				}

			}
		}
	}

	boolean checkMSTEdge(long weight) {
		for (int i = 1; i < parrents.length; i++) {
			if (parrents[i] >= 0) {
				if (weight == graph[i][parrents[i]]) {
					return true;
				}
			}
		}
		return false;
	}

	boolean isOfficialMST(int i1, int i2) {
		return officialMST[i1][i2] || officialMST[i2][i1];
	}

	private boolean isNotInComponent(int i1, int j1) {
//		for (int k = 0; k < graph.length; k++) {
//			if (firstComponent[i1][k] == true && firstComponent[j1][k] == false) {
//				return false;
//			}
//		}
		boolean leftCom = true;
		boolean rightCom = true;
		System.out.println("check " + i1 + " " + j1);
		//check different verts 
		for (int i = 0; i < graph.length; i++) {
			if (firstComponent[i1][i] == true || firstComponent[i][j1]) {
				leftCom = false;
			}
			if (inverseComponent[j1][i] == true || inverseComponent[i][i1]) {
				rightCom = false;
			}
		}
		//check for same component
		boolean sameLeftComponent = true;
		boolean sameRightComponent = true;
//		for (int i = 0; i < graph.length; i++) {
//			if (firstComponent[i1][i] == true) {
//				sameLeftComponent = false;
//			}
//			if (firstComponent[j1][i] == true) {
//				sameRightComponent = true;
//			}
//
//			if (inverseComponent[i1][i] == true) {
//				sameRightComponent = false;
//			}
//			if (inverseComponent[j1][i] == true) {
//				sameRightComponent = true;
//			}
//
//		}

		for (int i = 0; i < graph.length; i++) {
			if (firstComponent[i1][i] == true && firstComponent[i][j1]) {
				sameLeftComponent = false;
			}
			if (inverseComponent[j1][i] == true || inverseComponent[i][i1]) {
				sameRightComponent = false;
			}
		}
//		if (firstComponent[i1][j1] == false && inverseComponent[i1][j1] == false) {
//			return false;
//		}
//		else if (firstComponent[i][j] == false && inverseComponent[i][j] == true) {
//			return false;
//		}
//		return leftCom && rightCom && sameLeftComponent && sameRightComponent;
//		return leftCom && rightCom;
		boolean a = false;
		boolean b = false;
		boolean c = false;
		boolean d = false;
		for (int i = 0; i < graph.length; i++) {
			if (firstComponent[i1][i] == true) {
				a = true;
			}
			if (firstComponent[j1][i] == true) {
				b = true;
			}
		}

		for (int i = 0; i < graph.length; i++) {
			if (inverseComponent[i1][i] == true) {
				c = true;
			}
			if (inverseComponent[j1][i] == true) {
				d = true;
			}

		}

		if (c && d) {
			return true;
		} else if (a && b) {
			return true;
		}
		return false;
	}
}
