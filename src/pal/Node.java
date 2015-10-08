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
public class Node {

	int b1;
	int b2;
	int price;

	public Node(int b1, int b2, int price) {
		this.b1 = b1;
		this.b2 = b2;
		this.price = price;
	}

	public int getB1() {
		return b1;
	}

	public int getB2() {
		return b2;
	}

	public int getPrice() {
		return price;
	}

}
