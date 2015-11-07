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
	boolean[][] graph;

	public Certificate(boolean[][] graph) {
		this.closed = new boolean[graph.length];
		this.graph = graph;
	}

	public boolean[] dfs(int index) {
		boolean isLeaf = true;
		ArrayList<boolean[]> subCerts = new ArrayList<>();
		closed[index] = true;
		for (int i = 0; i < graph.length; i++) {
			if (graph[index][i] && !closed[i]) {
				isLeaf = false;
				boolean[] subCert = dfs(i);
				subCerts.add(subCert);
			}
		}

		if (isLeaf) {
			boolean[] cert = {false, true};
			return cert;
		}

		return sortSubCerts(subCerts);
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
		Iterator ite = pq.iterator();
		for (int i = 0; i < pq.size(); i++) {
//			String s = pq.poll();
			String s = (String) ite.next();
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '1') {
					subCert[subCertPointer] = true;
				} else {
					subCert[subCertPointer] = false;
				}
				subCertPointer++;
			}
			System.out.println(s);
		}
		subCert[0] = false;
		subCert[subCert.length - 1] = true;
		return subCert;
	}
}
