/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal04;

/**
 *
 * @author Thang Do
 */
public class Main {

	static int A, C, M, K, N;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		A = 5;
		C = 11;
		M = 8;
		K = 1;
		N = 4;
		Generator ge = new Generator(A, C, M, K, N);
//		ge.generate(0);
		
	}

}
