/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal03;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Thang Do
 */
public class TreeCert {

	boolean[][] graphA;
	boolean[][] graphB;
	ArrayList<String>[] certs;
	boolean[] closed;
	int root = -1;

	public TreeCert(boolean[][] graphA, boolean[][] graphB) {
		this.graphA = graphA;
		this.graphB = graphB;
		certs = new ArrayList[graphB.length];
		this.closed = new boolean[graphB.length];
		initCerts();
	}

	private void initCerts() {
		for (int i = 0; i < graphB.length; i++) {
			certs[i] = new ArrayList<>();
		}
	}

	public boolean[] findLeafs(boolean[][] graph) {
		boolean[] leaves = new boolean[graph.length];
		for (int i = 0; i < graph.length; i++) {
			int degree = 0;
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j] && !closed[j]) {
					degree++;
				}
			}
			if (degree == 1) {
				leaves[i] = true;
				//inicializace listů
//				certs[i].add("01");
			}
		}
		return leaves;
	}

	public int findParrent(int index, boolean[][] graph) {
		for (int i = 0; i < graph.length; i++) {
			if (graph[index][i]) {
				return i;
			}
		}
		return -1;
	}

	void computeCert(boolean[][] graph) {
		int graphSize = graph.length;
		boolean[] leaves = findLeafs(graph);
		initLeaves(leaves);
		while (true) {
			boolean nothingLeft = true;
			boolean[] parrents = new boolean[graph.length];
			for (int i = 0; i < graph.length; i++) {
				if (leaves[i] && !closed[i]) {
					nothingLeft = false;
					int parrentIndex = findParrent(i, graph);
//					System.out.println("list " + parrentIndex);
					addCertToParrent(i, parrentIndex);
					parrents[parrentIndex] = true;
					removeLeaf(i);
					graphSize--;
				}
			}
			int parrentCounter = 0;
			for (int i = 0; i < graph.length; i++) {
				if (parrents[i]) {
					makeCertForParrent(i);
					parrentCounter++;
				}
			}
//			System.out.println(parrentCounter);
			leaves = findLeafs(graph);

			if (nothingLeft) {
				break;
			}
			if (parrentCounter == 2) {
				System.out.println(makeCert2Left(parrents));
				break;
			}
		}
	}

	void addCertToParrent(int from, int to) {
//		Collections.sort(certs[from]);
		for (int i = 0; i < certs[from].size(); i++) {
			certs[to].add(certs[from].get(i));
		}
	}

	void makeCertForParrent(int index) {
		Collections.sort(certs[index]);
		StringBuilder sb = new StringBuilder();
		sb.append("0");
		for (int i = 0; i < certs[index].size(); i++) {
			sb.append(certs[index].get(i));
		}
		sb.append("1");
		certs[index].clear();
		certs[index].add(sb.toString());
//		System.out.println(index + " " + sb.toString());
	}

	String makeCert2Left(boolean[] parrents) {
		String[] cert = new String[2];
		int certPointer = 0;
		for (int i = 0; i < parrents.length; i++) {
			if (parrents[i]) {
				cert[certPointer] = certs[i].get(0);
				certPointer++;
			}
		}

		StringBuilder sb = new StringBuilder();
		if (cert[0].compareTo(cert[1]) > 0) {
			sb.append(cert[0]);
			sb.append(cert[1]);
			return sb.toString();
		} else {
			sb.append(cert[0]);
			sb.append(cert[1]);
			return sb.toString();
		}
	}

	private void removeLeaf(int i) {
		closed[i] = true;
	}

	private void initLeaves(boolean[] leaves) {
		for (int i = 0; i < leaves.length; i++) {
			if (leaves[i]) {
				certs[i].add("01");
			}

		}
	}
}
