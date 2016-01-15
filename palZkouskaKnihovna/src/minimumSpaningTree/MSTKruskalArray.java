/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimumSpaningTree;

import java.util.PriorityQueue;

/**
 *
 * @author Thang Do
 */
//otestováno funguje
public class MSTKruskalArray {

	//pointry na rodiče stromu tedy kořene;
	int[] set;

	int[] gWeight;
	int[] gEdgeA;
	int[] gEdgeB;

	int[] sptWeight;
	int[] sptEdgeA;
	int[] sptEdgeB;

	public MSTKruskalArray(int[] gWeight, int[] gEdgeA, int[] gEdgeB) {
		this.gWeight = gWeight;
		this.gEdgeA = gEdgeA;
		this.gEdgeB = gEdgeB;

		this.sptWeight = new int[gWeight.length];
		this.sptEdgeA = new int[gWeight.length];
		this.sptEdgeB = new int[gWeight.length];

		this.set = new int[gWeight.length];
		init();
	}

	void init() {
		for (int i = 0; i < gWeight.length; i++) {
			makeSet(i, i);
		}

		quickSort(gWeight, gEdgeA, gEdgeB, 0, gWeight.length - 1);

	}

	public void kruskal() {
		int edgeInACounter = 0;
		int counter = 0;
		while (counter < gWeight.length) {
			int weight = gWeight[counter];
			int u = gEdgeA[counter];
			int v = gEdgeB[counter];

//			System.out.println(findSet(u) + " : " + findSet(v));
			if (findSet(u) != findSet(v)) {
				sptWeight[edgeInACounter] = weight;
				sptEdgeA[edgeInACounter] = u;
				sptEdgeB[edgeInACounter] = v;
				union(u, v);
				edgeInACounter++;
			}
			counter++;
		}
	}

	void makeSet(int indexVert, int root) {
		set[indexVert] = root;
	}

	int findSet(int index) {
		return set[index];
	}

	void union(int u, int v) {
		int oldSetU = set[u];
		int valinSetV = set[v];
		for (int i = 0; i < gWeight.length; i++) {
			if (set[i] == oldSetU) {
				set[i] = valinSetV;
			}
		}
	}

	//spočítá cenu spt
	public int sptPrice() {
		int price = 0;
		for (int i = 0; i < sptWeight.length; i++) {
			price += sptWeight[i];
		}
		return price;
	}

	//*******************************************
	// QUICK SORT	
	//*******************************************
	public void quickSort(int array[], int[] gEdgeA, int[] gEdgeB, int start, int end) {
		int i = start;                          // index of left-to-right scan
		int k = end;                            // index of right-to-left scan

		if (end - start >= 1) // check that there are at least two elements to sort
		{
			int pivot = array[start];       // set the pivot as the first element in the partition

			while (k > i) // while the scan indices from left and right have not met,
			{
				while (array[i] <= pivot && i <= end && k > i) // from the left, look for the first
				{
					i++;                                    // element greater than the pivot
				}
				while (array[k] > pivot && k >= start && k >= i) // from the right, look for the first
				{
					k--;                                        // element not greater than the pivot
				}
				if (k > i) // if the left seekindex is still smaller than
				{
					swap(array, i, k);                      // the right index, swap the corresponding elements
					swap(gEdgeA, i, k);          // after the indices have crossed, swap the last element in
					swap(gEdgeB, i, k);          // after the indices have crossed, swap the last element in
				}
			}
			swap(array, start, k);          // after the indices have crossed, swap the last element in
			swap(gEdgeA, start, k);          // after the indices have crossed, swap the last element in
			swap(gEdgeB, start, k);          // after the indices have crossed, swap the last element in
			// the left partition with the pivot 
			quickSort(array, gEdgeA, gEdgeB, start, k - 1); // quicksort the left partition
			quickSort(array, gEdgeA, gEdgeB, k + 1, end);   // quicksort the right partition
		} else // if there is only one element in the partition, do not do any sorting
		{
			return;                     // the array is sorted, so exit
		}
	}

	public void swap(int array[], int index1, int index2) // pre: array is full and index1, index2 < array.length
	// post: the values at indices 1 and 2 have been swapped
	{
		int temp = array[index1];           // store the first value in a temp
		array[index1] = array[index2];      // copy the value of the second into the first
		array[index2] = temp;               // copy the value of the temp into the second
	}
}
