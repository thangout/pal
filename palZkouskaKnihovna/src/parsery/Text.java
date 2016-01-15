/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parsery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Thang Do
 */
public class Text {

	public static void main(String[] args) throws IOException {
		int N, M, cI, cD, cR;
		String text;
		String pattern;

//		BufferedReader in = new BufferedReader(new FileReader("pub07.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String firstLine = in.readLine();

		StringTokenizer tokenizer = new StringTokenizer(firstLine);
		N = Integer.valueOf(tokenizer.nextToken());
		M = Integer.valueOf(tokenizer.nextToken());
		cI = Integer.valueOf(tokenizer.nextToken());
		cD = Integer.valueOf(tokenizer.nextToken());
		cR = Integer.valueOf(tokenizer.nextToken());

		text = in.readLine();
		pattern = in.readLine();
	}
	
}
