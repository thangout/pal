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
public class Edge {
	Node fromNode;
	Node toNode;	
	long price;
	boolean inSPT;

	public Edge(Node fromNode, Node toNode, long price) {
		this.fromNode = fromNode;
		this.toNode = toNode;
		this.price = price;
	}

	public Node getToNode() {
		return toNode;
	}

	public void setToNode(Node toNode) {
		this.toNode = toNode;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public boolean isInSPT() {
		return inSPT;
	}

	public void setInSPT(boolean inSPT) {
		this.inSPT = inSPT;
	}

	public Node getFromNode() {
		return fromNode;
	}

	public void setFromNode(Node fromNode) {
		this.fromNode = fromNode;
	}

	
}
