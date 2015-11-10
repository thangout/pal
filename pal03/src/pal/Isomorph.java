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
public class Isomorph {

	boolean[][] graphA;
	boolean[][] graphB;
	boolean[] leaves;
	boolean[] isoVerts;
	TreeCert tc;

	public Isomorph(boolean[][] graphA, boolean[][] graphB) {
		this.graphA = graphA;
		this.graphB = graphB;
		this.leaves = new boolean[graphB.length];
		this.isoVerts = new boolean[graphB.length];
		tc = new TreeCert(graphA, graphB);
		leaves = tc.findLeafs(graphB);
	}

	void findIsoVert() {
		String certA = tc.computeCert(graphA, -1);
		for (int i = 0; i < graphB.length; i++) {
			if (leaves[i]) {
				String certB = tc.computeCert(graphB, i);
				if (certA.equals(certB)) {
					isoVerts[i] = true;
				}
				addLeaf(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < isoVerts.length; i++) {
			if (isoVerts[i]) {
				sb.append(i);
				sb.append(" ");
			}
		}
		System.out.println(sb.toString());
	}

	void removeLeaf(int index) {
		tc.closed[index] = true;
	}

	void addLeaf(int index) {
	}

}
