/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal05;

/**
 *
 * @author Thang Do
 */
public class Automat {

	int S, A, F, P, N, L;
	int[][] states;
	String[] examples;

	public Automat(int S, int A, int F, int P, int N, int L, int[][] states, String[] examples) {
		this.S = S;
		this.A = A;
		this.F = F;
		this.P = P;
		this.N = N;
		this.L = L;
		this.states = states;
		this.examples = examples;
	}
	

}
