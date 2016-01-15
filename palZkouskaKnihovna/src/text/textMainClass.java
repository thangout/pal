/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text;

/**
 *
 * @author Thang Do
 */
public class textMainClass {

	public static void main(String[] args) {
		String pattern = "rose";
		String input = "mrosedd";

		HammingDistanceDynamic hm = new HammingDistanceDynamic(pattern, input);
		hm.print();

		TextSearchNaive ts = new TextSearchNaive();
		System.out.println(ts.search(pattern, input));
		
		
	}

}
