/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pal;

import java.util.Comparator;

/**
 *
 * @author Thang Do
 */
public class VertexComparator implements Comparator<Vertex>  {

	@Override
	public int compare(Vertex t, Vertex t1) {
		return (int) (t.getWeight() - t1.getWeight());
	}
	
}
