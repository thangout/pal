/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfs;

import java.util.ArrayList;
import minimumSpaningTree.MSTKruskal;
import minimumSpaningTree.MSTKruskalArray;
import ruzne.FermatTest;
import ruzne.GreyCode;
import strongConnectedComponents.DFSKosaraju;
import strongConnectedComponents.Kosaraju;

/**
 *
 * @author Thang Do
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		/**
		 * **********************************************
		 * DFS a BFS test **********************************************
		 */
		int[][] graph = new int[4][4];
		graph[0][1] = 1;
		graph[0][2] = 1;
		graph[2][3] = 1;
		graph[3][1] = 1;

		DepthFirstSearch df = new DepthFirstSearch(graph);
		System.out.println("*************DFS test*************");
		df.startDfsRecursive();
		System.out.println("*************BFS test*************");

		BreadthFirstSearch bf = new BreadthFirstSearch(graph);
		bf.bfs(0);

		/**
		 * **********************************************
		 * Minimum Spanning Tree test
		 * **********************************************
		 */
		System.out.println("*************Minimum spanning tree test*************");
		int[][] graph2 = new int[4][4];
		graph2[0][1] = 1;
		graph2[0][2] = 3;
		graph2[2][3] = 2;
		graph2[3][1] = 9;
		graph2[3][0] = 19;

		graph2[1][0] = 1;
		graph2[2][0] = 3;
		graph2[3][2] = 2;
		graph2[1][3] = 9;
		graph2[3][0] = 19;
		MSTKruskal mst = new MSTKruskal(graph2);
		mst.kruskal();
		System.out.println("SPT price " + mst.sptPrice());

		/**
		 * **********************************************
		 * Minimum Spanning Tree test
		 * **********************************************
		 */
		System.out.println("*************Minimum spanning in array tree test*************");

		int[] weight = {19, 3, 2, 9, 1};
		int[] u = {3, 0, 2, 3, 0};
		int[] v = {0, 2, 3, 1, 1};
		MSTKruskalArray mstArray = new MSTKruskalArray(weight, u, v);
		mstArray.kruskal();
		System.out.println("SPT price " + mstArray.sptPrice());

		/**
		 * **********************************************
		 * Kosraju strong connected components test
		 * **********************************************
		 */
		System.out.println("************* Kosaraju test*************");
		int[][] graph3 = new int[4][4];
		graph3[0][1] = 1;
		graph3[0][2] = 3;
		graph3[2][3] = 2;
		graph3[3][1] = 9;
		graph3[3][0] = 19;
		Kosaraju kosaraju = new Kosaraju(graph3);
		kosaraju.kosaraju();

		/**
		 * **********************************************
		 * Grey Code test *********************************************
		 */
		System.out.println("************* GreyCode test*************");
		GreyCode gc = new GreyCode();
		ArrayList<Integer> T = new ArrayList<>();
		T.add(0);
		T.add(8);
		T.add(1);
		System.out.println(gc.binaryToGrey(7));
		System.out.println(gc.greyCodeRank(3, T));

		/**
		 * **********************************************
		 * Fermat test *********************************************
		 */
		System.out.println("************* Fermat test*************");
		FermatTest fermat = new FermatTest();
		System.out.println("11 is prime "+fermat.fermatTest(11, 100));
		System.out.println("10 is prime "+fermat.fermatTest(10, 100));

	}

}
