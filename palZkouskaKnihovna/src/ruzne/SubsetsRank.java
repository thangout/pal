/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruzne;

import java.util.ArrayList;

/**
 *
 * @author Thang Do
 */
public class SubsetsRank {

	public int subsetLexRank(int size, ArrayList<Integer> T) {
		//rank
		int r = 0;
		for (int i = 0; i < size; i++) {
			if (T.contains(i)) {
				r = (int) (r + Math.pow(2, size - i));
			}
		}

		return r;
	}

	public ArrayList<Integer> subsetLexUnrank(int size, int rank) {
		ArrayList<Integer> t = new ArrayList();
		for (int i = 0; i < size; i++) {
			if (rank % 2 == 1) {
				t.add(i);
			}

			rank = (int) Math.floor(rank / 2);
		}
		return t;

	}

//	private boolean containsInT(int i) {
//		for (int j = 0; j < T.length; j++) {
//			if (i == T[j]) {
//				return true;
//			}
//		}
//		return false;
//	}
}
