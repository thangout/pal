/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author Thang Do
 */
public class SpaTree {

	Vertex[] vertices;
	int numOfConnections;

	public SpaTree(Vertex[] vertices, int numOfConnections) {
		this.vertices = vertices;
		this.numOfConnections = numOfConnections;
	}

	public void prim() {
		PriorityQueue<Vertex> pq = new PriorityQueue<>(numOfConnections, new VertexComparator());
		pq.add(new Vertex(4, 30));
		pq.add(new Vertex(0, 100));
		pq.add(new Vertex(1, 40));
		Iterator<Vertex> it = pq.iterator();

//		for (int i = 0; i < pq.size(); i++) {
//			System.out.println(pq.poll().getWeight());	
//		}
		while (it.hasNext()) {
			System.out.println(it.next().getWeight());
			
		}
		
	}
}
