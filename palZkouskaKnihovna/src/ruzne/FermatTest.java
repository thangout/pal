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
public class FermatTest {

	public boolean fermatTest(int n, int k) {
		int min = 2;
		int max = n - 2;
		for (int i = 1; i < k; i++) {
			int a = (int) ((Math.random() * (max - min + 1)) + 2);
			if (Math.pow(a, n - 1) % n != 1) {
				//is composite
				return false;
			}
		}
		//is prime
		return true;
	}
}
