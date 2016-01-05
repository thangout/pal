/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimumSpaningTree;

import java.util.Comparator;

/**
 *
 * @author Thang Do
 */
public class EdgeWeightComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge t, Edge t1) {
		return t.weight - t1.weight;
	}

}
