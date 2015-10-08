/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thang Do
 */
public class Main {
	
	static Node[] edges;
	public static void main(String[] args) throws IOException {
		try {
			BufferedReader in = new BufferedReader(new FileReader("pub03.in"));
			String firstLine = in.readLine();

			// Get basic information c1 and c2 are criterias 
			StringTokenizer tokenizer = new StringTokenizer(firstLine);
			int numOfBuildings = Integer.valueOf(tokenizer.nextToken());
			int numOfConnections = Integer.valueOf(tokenizer.nextToken());
			int c1 = Integer.valueOf(tokenizer.nextToken());
			int c2 = Integer.valueOf(tokenizer.nextToken());

			edges = new Node[numOfConnections];

			while(firstLine != null){
				firstLine = in.readLine();
				System.out.println(firstLine);
				tokenizer = new StringTokenizer(firstLine);

				// basic connection info 
				int b1 = Integer.valueOf(tokenizer.nextToken());
				int b2 = Integer.valueOf(tokenizer.nextToken());
				int price = Integer.valueOf(tokenizer.nextToken());
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
