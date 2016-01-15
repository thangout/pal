/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulohy.stanice;

/**
 *
 * @author Thang Do
 */
public class Geometry {

	public Geometry() {
	}

	//spočítá vzdálenost 2 bodů
	public double calculatePointDistance(int a1, int a2, int b1, int b2) {
		double res = Math.sqrt(Math.pow(b1 - a1,2) + Math.pow(b2 - a2,2));
		return res;
	}

	// Returns 1 if the lines intersect, otherwise 0. In addition, if the lines 
	// intersect the intersection point may be stored in the floats i_x and i_y.
	boolean get_line_intersection(float p0_x, float p0_y, float p1_x, float p1_y,
		float p2_x, float p2_y, float p3_x, float p3_y) {

		float s1_x, s1_y, s2_x, s2_y;
		s1_x = p1_x - p0_x;
		s1_y = p1_y - p0_y;
		s2_x = p3_x - p2_x;
		s2_y = p3_y - p2_y;

		float s, t;
		s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / (-s2_x * s1_y + s1_x * s2_y);
		t = (s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / (-s2_x * s1_y + s1_x * s2_y);
		
		float i_x,i_y;
		if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
			// Collision detected
			i_x = p0_x + (t * s1_x);
			i_y = p0_y + (t * s1_y);
			return true;
		}

		return false; // No collision
	}
}
