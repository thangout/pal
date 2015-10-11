/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Thang Do
 */
public class SpanningTree {

	Node[] nodes;
	ArrayList<Node> visited;
	int weightSTP = 0;
	ArrayList<Edge> sptEdges;

	public SpanningTree(Node[] nodes) {
		this.nodes = nodes;
		this.visited = new ArrayList<Node>();
		this.sptEdges = new ArrayList<>();
	}

	public void makeSpanningTree() {
		addVisited(nodes[0]);
		while (visited.size() != nodes.length) {
			Node node = pickCheapestConnection(findPotentialEdges());
			addVisited(node);
		};
	}

	private void addVisited(Node node) {
//		System.out.println("adding" + node.name);
		node.setInST(true);
		visited.add(node);
	}

	private boolean containsVisited(Node node) {
		return visited.contains(node);
	}

	private ArrayList<Edge> findPotentialEdges() {
		ArrayList<Edge> potEdges = new ArrayList<>();
		for (int i = 0; i < visited.size(); i++) {
			visited.get(i).getEdgesOut().iterator();
			Iterator ite = visited.get(i).getEdgesOut().iterator();
			while (ite.hasNext()) {
				Edge item = (Edge) ite.next();
				if (!containsVisited(item.getToNode()) && !item.isInSPT()) {
					potEdges.add(item);
				}
			}
		}
		return potEdges;
	}

	private Node pickCheapestConnection(ArrayList edges) {
		Iterator ite = edges.iterator();
		Node bestCandidate = null;
		Edge bestEdge = null;
		long bestPrice = 0;
		while (ite.hasNext()) {
			Edge item = (Edge) ite.next();
			if (bestCandidate == null) {
				bestCandidate = item.getToNode();
				bestPrice = item.getPrice();
				bestEdge = item;
			}

			if (bestPrice > item.getPrice()) {
				bestCandidate = item.getToNode();
				bestPrice = item.getPrice();
				bestEdge = item;
			}
		}
//		System.out.println(bestCandidate.name + " - cheapset->" + bestPrice);
		makeEdgeSPT(bestEdge);
		return bestCandidate;
	}

	private void makeEdgeSPT(Edge edge) {
		edge.setInSPT(true);
		sptEdges.add(edge);
	}

	public void printSPT(Node node) {
		for (int i = 0; i < sptEdges.size(); i++) {
			System.out.println(sptEdges.get(i).getFromNode().name + "--(" + sptEdges.get(i).getPrice() + ")-->" + sptEdges.get(i).getToNode().name);
		}
	}

	public long getSPTcost() {
		long cost = 0;
		for (int i = 0; i < sptEdges.size(); i++) {
			cost += sptEdges.get(i).getPrice();
		}
		return cost;
	}
}
