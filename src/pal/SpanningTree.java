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
	Edge toRemoveEdge;
	ArrayList<Edge> firstComponent;
	ArrayList<Edge> secondComponent;

	public SpanningTree(Node[] nodes) {
		this.nodes = nodes;
		this.visited = new ArrayList<Node>();
		this.sptEdges = new ArrayList<>();
		this.firstComponent = new ArrayList();
		this.secondComponent = new ArrayList();
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
		saveEdgeToRemove(bestEdge);
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

	private void saveEdgeToRemove(Edge edge) {
		if (toRemoveEdge == null) {
			toRemoveEdge = edge;
		} else {
			if (edge.getPrice() < toRemoveEdge.getPrice()) {
				toRemoveEdge = edge;
			}

		}
	}

	private void removeCheapestEdge() {
		sptEdges.remove(toRemoveEdge);
//		System.out.println("removing edge with price: " + toRemoveEdge.price);
	}

	public void findComponent() {
		removeCheapestEdge();
		Node fromNode = toRemoveEdge.getFromNode();
		Node toNode = toRemoveEdge.getToNode();
//		for (int i = 0; i < sptEdges.size(); i++) {
//			if (sptEdges.get(i).getToNode() == fromNode) {
//				firstComponent.add(sptEdges.get(i));
//				sptEdges.remove(i);
//			}
//
////		}
		findPrec(fromNode, firstComponent);
		findAfter(fromNode, firstComponent);
//		findPrec(toNode, secondComponent);
//		findAfter(toNode, secondComponent);
	}

	private void findPrec(Node node, ArrayList<Edge> component) {
//		ArrayList<Edge> precEdges = new ArrayList<>();
		for (int i = 0; i < sptEdges.size(); i++) {
			if (sptEdges.get(i).getToNode() == node) {
//				precEdges.add(edges.get(i));
//				findPrec(sptEdges, edges.get(i).fromNode);
				component.add(sptEdges.get(i));
				findPrec(sptEdges.get(i).getFromNode(), component);
//				sptEdges.remove(i);
			}
		}
	}

	private void findAfter(Node node, ArrayList<Edge> component) {
		for (int i = 0; i < sptEdges.size(); i++) {
			if (sptEdges.get(i).getFromNode() == node && !component.contains(sptEdges.get(i))) {
				component.add(sptEdges.get(i));
				findAfter(sptEdges.get(i).getToNode(), component);
//				sptEdges.remove(i);
			}
		}
	}

	public ArrayList searchSusbtiteEdges(long c1, long c2) {
		ArrayList<Edge> subsEdges = new ArrayList<>();
		for (int i = 0; i < nodes.length; i++) {
			ArrayList<Edge> edges = nodes[i].getEdgesOut();
			for (int j = 0; j < edges.size(); j++) {
				Edge it = edges.get(j);
				if (it.getPrice() <= c2 && it.getPrice() >= c1
					&& !it.isInSPT() && !containsComponent(it.getFromNode(), it.getToNode(), firstComponent)) {
					if (it.getFromNode().name < it.getToNode().name) {
					}
					subsEdges.add(it);
				}
			}
		}

		return subsEdges;
	}

	public void dumpFirstComponent() {
		System.out.println("___Dumping component 1");
		for (int i = 0; i < firstComponent.size(); i++) {
			System.out.println(firstComponent.get(i).getPrice());
		}
	}

	public void dumpSecondComponent() {
		System.out.println("___Dumping component 2");
		for (int i = 0; i < secondComponent.size(); i++) {
			System.out.println(secondComponent.get(i).getPrice());
		}
	}

	private boolean containsComponent(Node to, Node from, ArrayList<Edge> component) {
		for (int i = 0; i < component.size(); i++) {
			Edge it = component.get(i);
			if ((it.getFromNode() == from && it.getToNode() != to)
				|| (it.getFromNode() != from && it.getToNode() == to)) {
				return false;
			}
		}
		return true;
	}
}
