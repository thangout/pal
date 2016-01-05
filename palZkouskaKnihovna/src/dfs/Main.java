/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dfs;

import minimumSpaningTree.MSTKruskal;

/**
 *
 * @author Thang Do
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		/*
		DFS test
		*/
		int[][] graph = new int[4][4];

		graph[0][1] = 1;
		graph[0][2] = 1;

		graph[2][3] = 1;

		graph[3][1] = 1;


		DepthFirstSearch df = new DepthFirstSearch(graph);
		System.out.println("");
		df.startDfsRecursive();
		System.out.println("");

		BreadthFirstSearch bf = new  BreadthFirstSearch(graph);
		bf.bfs(0);

		/*
		MST test	
		*/
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

	}
	
}
