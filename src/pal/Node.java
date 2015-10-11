/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *
 * @author Thang Do
 */
public class Node {

	int name;
	HashMap neighbours;
	ArrayList<Edge> edgesOut;

	// is in spanning tree
	boolean inST;

	public Node(int b1) {
		this.name = b1;
		this.neighbours = new HashMap<Node, Integer>();
		this.edgesOut = new ArrayList<>();
	}

	public void addNeighbour(Node neighbour, int price) {
		neighbours.put(neighbour, price);
	}

	public HashMap getNeighbours() {
		return neighbours;
	}

	public void addEdgeOut(Node from, Node to, long price) {
		edgesOut.add(new Edge(from, to, price));
	}

	public void printNeighbours() {
		Iterator ite = neighbours.entrySet().iterator();
		while (ite.hasNext()) {
			Entry<Node, Integer> item = (Entry<Node, Integer>) ite.next();
			System.out.println(this.name + " " + item.getKey().name + " " + item.getValue());
		}
	}

	public boolean isInST() {
		return inST;
	}

	public void setInST(boolean inST) {
		this.inST = inST;
	}

	public ArrayList<Edge> getEdgesOut() {
		return edgesOut;
	}
}
