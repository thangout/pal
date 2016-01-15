/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ruzne;

/**
 *
 * @author Thang Do
 */
public class BinaryTable {

	//generuje 0 a 1 table
	public void generate(){
		int x = 100;
		for (int i = 0; i < x; i++) {
			StringBuilder sb = new StringBuilder();
			String bin = Integer.toBinaryString(i);
			for (int j = 0; j < 10 - bin.length(); j++) {
				sb.append(0);
			}
			sb.append(bin);
			System.out.println(sb.toString());
//			System.out.println(Integer.toBinaryString(i));
		}
	}
}
