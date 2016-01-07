/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strongConnectedComponents;

import dfs.DepthFirstSearch;
import java.util.Stack;

/**
 *
 * @author Thang Do
 */
public class Kosaraju {

	//predpokládá se orientovaný graf, stím že dolní polovina bude symetrická 
	//tedy bude tam druhý směr
	int[][] graph;
	DFSKosaraju dfs;

	public Kosaraju(int[][] graph) {
		this.graph = graph;
		dfs = new DFSKosaraju(graph);
	}

	public void kosaraju() {
		//prvni čast napushuje zavreny nody do stacku
		dfs.startDfsRecursive();


		dfs.clearPermanentMark();

		//hrany už jsou otočené při procházení matice
		dfs.startDfsRecursiveComponent();

		dfs.printComponents(graph.length);
		System.out.println("Number of strongly connected components: " + dfs.componentCounter);
	}

}
