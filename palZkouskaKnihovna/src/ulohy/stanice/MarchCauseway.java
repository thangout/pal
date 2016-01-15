/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulohy.stanice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Thang Do
 */
public class MarchCauseway {

	float[][] distances;
	boolean[][][][] intersecion;
	Point[] points;
	Geometry geom;
	int D;

	public MarchCauseway(Point[] points, int D) {
		this.points = points;
		this.distances = new float[points.length][points.length];
		this.intersecion = new boolean[points.length][points.length][points.length][points.length];
		geom = new Geometry();
		this.D = D;
		startAlg();

	}

	void startAlg() {
		preCalcDistances();
		preCalcIntersections();
		startDfs();
	}

	void startDfs() {
		for (int i = 0; i < points.length; i++) {
			boolean[] visited = new boolean[points.length];
			int[] marsh = new int[points.length + 1];
			int[] walked = dfsWalk(0, new boolean[points.length], i, new int[points.length + 1], 0);
		}
	}

	float countLengthOfMarsh(int[] marsh) {
		float res = 0f;
		for (int i = 1; i < marsh.length; i++) {
			res += distances[i - 1][i];
		}
		return res;
	}

	int[] dfsWalk(float soFarMarshDistance, boolean[] visited, int parrent, int[] marsh, int marshCounter) {
		visited[parrent] = true;
		marsh[marshCounter] = parrent;
		System.out.print(parrent + ", ");
		for (int i = 0; i < points.length; i++) {
			if (!visited[i]) {
				float currentDistance = (soFarMarshDistance + distances[parrent][i]);
				if (currentDistance <= D && !visited[i] && !checkIntersectionWithMarsh(visited, marsh, parrent, i, marshCounter)) {
//					boolean[] newVisited = new boolean[points.length]; 
//					System.arraycopy(visited, 0, newVisited, 0, visited.length);
					dfsWalk(currentDistance, visited, i, marsh, marshCounter++);
				}
			}
		}
		System.out.println("");
//		System.out.println(Math.ceil(soFarMarshDistance));
//		System.out.println(soFarMarshDistance);
		return marsh;
	}

	boolean checkIntersectionWithMarsh(boolean[] visited, int[] marsh, int previousPoint, int newPoint, int marshCounter) {
		for (int i = 1; i < marshCounter; i++) {
			boolean intersect = intersecion[marsh[i - 1]][marsh[i]][previousPoint][newPoint];
			if (intersect) {
				return true;
			}
		}
		return false;
	}

	void preCalcDistances() {
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				distances[i][j]
					= (float) geom.calculatePointDistance(points[i].x, points[i].y, points[j].x, points[j].y);
			}
		}
	}

	void preCalcIntersections() {
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int k = 0; k < points.length; k++) {
					for (int l = k + 1; l < points.length; l++) {
						intersecion[i][j][k][l]
							= geom.get_line_intersection(points[i].x, points[i].y, points[j].x, points[j].y,
								points[k].x, points[k].y, points[l].x, points[l].y);
					}
				}
			}
		}
	}
}
