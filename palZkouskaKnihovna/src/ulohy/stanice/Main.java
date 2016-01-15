/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulohy.stanice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author Thang Do
 */
public class Main {

	static Point[] points;

	public static void main(String[] args) throws IOException {
		int D = 0;

		// počet hran
		int numPoints = 0;
		BufferedReader in = new BufferedReader(new FileReader("uloha-marsh/pub01.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String firstLine = in.readLine();

		// Get basic information c1 and c2 are criterias 
		StringTokenizer tokenizer = new StringTokenizer(firstLine);
		while (tokenizer.hasMoreTokens()) {
			numPoints = Integer.valueOf(tokenizer.nextToken());
			D = Integer.valueOf(tokenizer.nextToken());
		}

		//weighted graph
		points = new Point[numPoints];

		int pointsCounter = 0;
		while ((firstLine = in.readLine()) != null) {
			tokenizer = new StringTokenizer(firstLine);

			// basic connection info 
			int ib1 = Integer.valueOf(tokenizer.nextToken());
			int ib2 = Integer.valueOf(tokenizer.nextToken());
			points[pointsCounter] = new Point(ib1, ib2);
			pointsCounter++;
		}

		MarchCauseway mc = new MarchCauseway(points, D);
		System.out.println(mc.intersecion[4][1][4][9]);
	}

}
