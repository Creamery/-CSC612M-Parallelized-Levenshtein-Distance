package com.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.main.Model;

/**
 * Algorithm referenced from:
 * 	Levenshtein Distance, in Three Flavors
 * 	by Michael Gilleland, Merriam Park Software
 * 	https://people.cs.pitt.edu/
 * 
 * @author Candy
 *
 */
public class Algorithm {
	private Model model;
	
	public Algorithm(Model model) {
		this.model = model;
	}
	
	public int levenshteinDistance(String s, String t) {
		
		int d[][]; // matrix
		int n; // length of s
		int m; // length of t
		int i; // iterates through s
		int j; // iterates through t
		char s_i; // ith character of s
		char t_j; // jth character of t
		int cost; // cost

	    // Step 1

		n = s.length();
		m = t.length();
		
		if(n == 0) {
			return m;
		}
		
		if(m == 0) {
			return n;
		}
		
		d = new int[n+1][m+1];

		// Step 2

		for(i = 0; i <= n; i++) {
			d[i][0] = i;
		}

		for(j = 0; j <= m; j++) {
			d[0][j] = j;
		}

		// Step 3
		long timeStart = System.nanoTime();
		for(i = 1; i <= n; i++) {

			s_i = s.charAt(i - 1);

			// Step 4
			for(j = 1; j <= m; j++) {
				t_j = t.charAt(j - 1);
				// Step 5
				if(s_i == t_j) {
					cost = 0;
				}
				else {
					cost = 1;
				}
				// Step 6
				d[i][j] = this.model.getMinimum(d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1] + cost);
			}
		}
		
		long timeEnd = System.nanoTime();
		long executionTime = timeEnd-timeStart;
		double seconds = (double)executionTime / 1000000000.0;
		
		System.out.println("STANDARD: "+d[n][m]);
		NumberFormat formatter = new DecimalFormat("#0.0000000000");
		System.out.println("Execution Time (ns): "+executionTime);
		System.out.println("Execution Time (sec): "+formatter.format(seconds));
		// Step 7
		return d[n][m];
	}
}
