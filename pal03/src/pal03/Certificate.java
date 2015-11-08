/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author Thang Do
 */
public class Certificate {

	boolean[] closed;
	boolean[] isList;
	boolean[][] graphA;
	boolean[][] graphB;
	boolean[] isomporhVert;

	public Certificate(boolean[][] graphA, boolean[][] graphB) {
		this.closed = new boolean[graphB.length];
		this.isList = new boolean[graphB.length];
		this.graphA = graphA;
		this.graphB = graphB;
		this.isomporhVert = new boolean[graphB.length];
	}

	public boolean[] dfs(int index, boolean[][] graph) {
		boolean isLeaf = true;
		ArrayList<boolean[]> subCerts = new ArrayList<>();
		closed[index] = true;
		for (int i = 0; i < graph.length; i++) {
			if (graph[index][i] && !closed[i]) {
				isLeaf = false;
				boolean[] subCert = dfs(i, graph);
				subCerts.add(subCert);
			}
		}

		if (isLeaf) {
			boolean[] cert = {false, true};
//			isList[index] = true;
			return cert;
		}

		return sortSubCerts(subCerts);
	}

	public void findLeafs(int index) {
		for (int i = 0; i < graphB.length; i++) {
			int degree = 0;
			for (int j = 0; j < graphB.length; j++) {
				if (graphB[i][j]) {
					degree++;
				}
			}
			if (degree == 1) {
				isList[i] = true;
			}

		}

	}

	private boolean[] sortSubCerts(ArrayList<boolean[]> list) {
		PriorityQueue<String> pq = new PriorityQueue<>();
		int sizeSubCert = 0;

		for (int i = 0; i < list.size(); i++) {
			boolean[] item = list.get(i);
			StringBuilder sb = new StringBuilder();
			sizeSubCert += item.length;
			for (int j = 0; j < item.length; j++) {
				if (item[j]) {
					sb.append('1');
				} else {
					sb.append('0');
				}
			}
			pq.add(sb.toString());
		}

		boolean[] subCert = new boolean[sizeSubCert + 2];
		int subCertPointer = 1;
//		Iterator ite = pq.iterator();
		for (int i = 0; i < pq.size(); i++) {
			String s = pq.poll();
//			String s = (String) ite.next();
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '1') {
					subCert[subCertPointer] = true;
				} else {
					subCert[subCertPointer] = false;
				}
				subCertPointer++;
			}
//			System.out.println(s);
		}
		subCert[0] = false;
		subCert[subCert.length - 1] = true;
		return subCert;
	}

	public void setGraph() {
		for (int i = 0; i < graphB.length; i++) {
			closed[i] = false;
		}
	}

	public int deleteEdge(int index) {
		closed[index] = true;
		return index;
	}

	public void recoverDeletedEdge(int index) {
		closed[index] = false;
	}

	public void findIsomporh() {
		boolean[] certA = dfs(0, graphA);
		printCert(certA);
		for (int i = 0; i < isList.length; i++) {
			if (isList[i]) {
				setGraph();
				int index = deleteEdge(i);
				//compute new cert for tree without an edge i
				boolean[] certB = null;
				if (i == 0) {
					certB = dfs(1, graphB);
				} else {
					certB = dfs(0, graphB);
				}
				if (compareCert(certA, certB)) {
					isomporhVert[index] = true;
				}
				recoverDeletedEdge(index);
			}
		}
	}

	public boolean compareCert(boolean[] certA, boolean[] certB) {
		printCert(certB);
		for (int i = 0; i < certA.length; i++) {
			if (certA[i] && certB[i]) {
			} else {
				return false;
			}
		}
		return true;
	}

	public void printCert(boolean[] cert) {
		for (int i = 0; i < cert.length; i++) {
			if (cert[i]) {
				System.out.print("1");
			} else {
				System.out.print("0");
			}
		}
		System.out.println("");
	}

}
