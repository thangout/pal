/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruzne;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Thang Do
 */
public class PriorQueue {

	public PriorQueue() {
		Test a = new Test();
		a.main(null);
	}

	public class Test {

		public void main(String[] args) {
			Comparator<String> comparator = new StringLengthComparator();
			PriorityQueue<String> queue
				= new PriorityQueue<String>(10, comparator);
			queue.add("short");
			queue.add("very long indeed");
			queue.add("medium");
			while (queue.size() != 0) {
				System.out.println(queue.remove());
			}
		}
	}

// StringLengthComparator.java
	public class StringLengthComparator implements Comparator<String> {

		@Override
		public int compare(String x, String y) {
			// Assume neither string is null. Real code should
			// probably be more robust
			// You could also just return x.length() - y.length(),
			// which would be more efficient.
			if (x.length() < y.length()) {
				return -1;
			}
			if (x.length() > y.length()) {
				return 1;
			}
			return 0;
		}
	}
}
