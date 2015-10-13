/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Thang Do
 */
public class Vertex {

	int name;
	long weight;
	ArrayList<Vertex> adjList;
	Vertex parrent;

	public Vertex(int name, long weight) {
		this.name = name;
		this.weight = weight;

	}

	public void addNeighbour(int name, long weight) {
		if (adjList == null) {
			adjList = new ArrayList<>();
		}
		adjList.add(new Vertex(name, weight));
	}

	public long getWeight() {
		return weight;
	}

	

}
