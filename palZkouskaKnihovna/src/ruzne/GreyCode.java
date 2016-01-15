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
public class GreyCode {

	//binary code rank to greyCode
	//binární číslo se dá taky zadat 0B101 například 
	public int binaryToGrey(int binaryCodeRank) {
		return binaryCodeRank ^ (binaryCodeRank >> 1);
	}

	//převod greyCodu na binární číslo
	public int greyToBinary(int greyCodeRank) {
		int B = 0;
		int n = Integer.bitCount(greyCodeRank) - 1;

		for (int i = 0; i < n; i++) {
			B = B << 1;
			B = B | (1 & ((B >> 1) ^ (greyCodeRank >> n)));
			greyCodeRank = greyCodeRank << 1;
		}
		return B;
	}

	//z grey kodu a spočíta jeho rank
	public int greyCodeRank(int size, ArrayList<Integer> T) {
		int r = 0;
		int b = 0;
		for (int i = size - 1; i >= 0; i--) {
			if (T.contains(i)) {
				b = 1 - b;
			}
			if (b == 1) {
				r = (int) (r + Math.pow(2, i));
			}
		}
		return r;
	}

	//vrátí rank pro greyuv kod
	public ArrayList<Integer> greyCodeUnrank(int size, int rank) {
		ArrayList<Integer> T = new ArrayList<>();
		int c = 0;
		for (int i = size - 1; i >= 0; i--) {
			int b = (int) Math.floor(rank / Math.pow(2, i));
			if (b != c) {
				T.add(size - i);
				c = b;
				rank = (int) (rank - (b * Math.pow(2, i)));
			}
		}
		return T;

	}

}
