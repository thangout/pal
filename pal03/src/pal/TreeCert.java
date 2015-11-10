/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

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
	boolean[] onlyLeaves;

	public TreeCert(boolean[][] graphA, boolean[][] graphB) {
		this.graphA = graphA;
		this.graphB = graphB;
		certs = new ArrayList[graphB.length];
		this.closed = new boolean[graphB.length];
		this.onlyLeaves = new boolean[graphB.length];
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
			if (!onlyLeaves[i]) {
				for (int j = 0; j < graph.length; j++) {
					if (graph[i][j] && !closed[j]) {
						degree++;
					}
				}
				if (degree == 1) {
					leaves[i] = true;
					onlyLeaves[i] = true;
				}
			}
		}
		return leaves;
	}

	public int findParrent(int index, boolean[][] graph) {
		for (int i = 0; i < graph.length; i++) {
			if (graph[index][i] && !closed[i]) {
				return i;
			}
		}
		return -1;
	}

	String computeCert(boolean[][] graph, int removeVertIndex) {
		clearClosed();
		if (removeVertIndex >= 0) {
			closed[removeVertIndex] = true;
		}
		String finalCert = null;
		boolean[] leaves = findLeafs(graph);
		initLeaves(leaves);
		while (true) {
			boolean[] parrents = new boolean[graph.length];
			for (int i = 0; i < graph.length; i++) {
				if (leaves[i] && !closed[i]) {
					int parrentIndex = findParrent(i, graph);
//					System.out.println("list " + parrentIndex);
					addCertToParrent(i, parrentIndex);
					parrents[parrentIndex] = true;
					removeLeaf(i);
				}
			}
			leaves = findLeafs(graph);
			for (int i = 0; i < graph.length; i++) {
				if (parrents[i] && leaves[i]) {
					makeCertForParrent(i);
				}
			}

			int[] closedCheck = checkClosed(graph.length);
			if (closedCheck[0] == 2) {
				finalCert = makeCert2Left(parrents);
				break;
			} else if (closedCheck[0] == 1) {
				finalCert = makeCertForParrent(closedCheck[1]);
				break;
			}
		}
//		System.out.println(finalCert);
		return finalCert;
	}

	void addCertToParrent(int from, int to) {
//		Collections.sort(certs[from]);
		for (int i = 0; i < certs[from].size(); i++) {
			certs[to].add(certs[from].get(i));
		}
	}

	String makeCertForParrent(int index) {
		Collections.sort(certs[index]);
		StringBuilder sb = new StringBuilder();
		sb.append("0");
		for (int i = 0; i < certs[index].size(); i++) {
			sb.append(certs[index].get(i));
		}
		sb.append("1");
		certs[index].clear();
		certs[index].add(sb.toString());
		return certs[index].get(0);
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
		if (cert[0].compareTo(cert[1]) < 0) {
			sb.append(cert[0]);
			sb.append(cert[1]);
//			System.out.println("cert size " + sb.toString().length());
			return sb.toString();
		} else {
			sb.append(cert[1]);
			sb.append(cert[0]);
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

	int[] checkClosed(int size) {
		int[] box = new int[2];
		int unclosed = 0;
		int lastIndex = -1;
		for (int i = 0; i < size; i++) {
			if (!closed[i]) {
				unclosed++;
				lastIndex = i;
			}
		}
		box[0] = unclosed;
		box[1] = lastIndex;
//		System.out.println("Unclosed " + unclosed + " last index = " + lastIndex);
		return box;
	}

	void clearClosed() {
		for (int i = 0; i < closed.length; i++) {
			closed[i] = false;
			certs[i].clear();
			onlyLeaves[i] = false;
		}
	}
}
