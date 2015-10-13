/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thang Do
 */
public class Main {

	static Node[] nodes;
	static Vertex[] vertices;

	public static void main(String[] args) throws IOException {
		SpaTree sptyc = new SpaTree(vertices, 3);
		sptyc.prim();
		int c1 = 0;
		int c2 = 0;
//		System.out.println("135\n0 1 28\n1 5 26\n10 14 25");
		try {
			BufferedReader in = new BufferedReader(new FileReader("pub01.in"));
//			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String firstLine = in.readLine();

			// Get basic information c1 and c2 are criterias 
			StringTokenizer tokenizer = new StringTokenizer(firstLine);
			int numOfBuildings = Integer.valueOf(tokenizer.nextToken());
			int numOfConnections = Integer.valueOf(tokenizer.nextToken());
			c1 = Integer.valueOf(tokenizer.nextToken());
			c2 = Integer.valueOf(tokenizer.nextToken());

			nodes = new Node[numOfBuildings];

			while ((firstLine = in.readLine()) != null) {
//				System.out.println(firstLine);
				tokenizer = new StringTokenizer(firstLine);

				// basic connection info 
				int ib1 = Integer.valueOf(tokenizer.nextToken());
				int ib2 = Integer.valueOf(tokenizer.nextToken());
				int price = Integer.valueOf(tokenizer.nextToken());

				if (nodes[ib1] == null) {
					nodes[ib1] = new Node(ib1);
				}
				if (nodes[ib2] == null) {
					nodes[ib2] = new Node(ib2);
				}
//				nodes[ib1].addNeighbour(nodes[ib2], price);
				nodes[ib1].addEdgeOut(nodes[ib1], nodes[ib2], price);
				nodes[ib2].addEdgeOut(nodes[ib2], nodes[ib1], price);
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

//		System.out.println("____Print STP");
		SpanningTree spt = new SpanningTree(nodes);
		spt.makeSpanningTree();
		StringBuilder builder = new StringBuilder();
		builder.append(spt.getSPTcost()).append("\n");
//		spt.printSPT(nodes[0]);
		System.out.println(spt.getSPTcost());

		spt.findComponent();
//		System.out.println("first component size:" + spt.firstComponent.size());
//		spt.dumpFirstComponent();
//		System.out.println("_____");
//		spt.dumpSecondComponent();
		ArrayList<Edge> list = spt.searchSusbtiteEdges(c1, c2);
		for (int i = 0; i < list.size(); i++) {
			Edge it = list.get(i);
//			System.out.println(list.get(i).getFromNode().name + " " + list.get(i).getToNode().name + " " + list.get(i).getPrice());
			if (it.getFromNode().name > it.getToNode().name) {
//				builder.append(it.getToNode().name).append(" ").append(it.getFromNode().name).append(" ").append(it.getPrice())
//					.append("\n");
				System.out.println(list.get(i).getToNode().name + " " + list.get(i).getFromNode().name + " " + list.get(i).getPrice());
			} else {
//				builder.append(it.getFromNode().name).append(" ").append(it.getToNode().name).append(" ").append(it.getPrice())
//					.append("\n");
				System.out.println(list.get(i).getFromNode().name + " " + list.get(i).getToNode().name + " " + list.get(i).getPrice());
			}

		}
//		System.out.println(builder.toString());

	}

	public void spannigTree(Node[] nodes) {

	}
}
