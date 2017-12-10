package com.model;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.main.Model;
import com.main.ComputeDistance;

/**
 * Algorithm referenced from:
 * 	Levenshtein Distance, in Three Flavors
 * 	by Michael Gilleland, Merriam Park Software
 * 	https://people.cs.pitt.edu/
 * 
 * @author Candy
 *
 */
public class ParallelAlgorithm {

	private Model model;
	private int[][] matrix;
	private String stringS;
	private String stringT;
	private int n;
	private int m;
	
	private int i;
	private int j;
	
	
	public ParallelAlgorithm(Model model) {
		this.model = model;
	}
	
	
	public int LD(String s, String t) {
		this.stringS = s;
		this.stringT = t;
		
		System.out.println("1: "+stringS);
		System.out.println("2: "+stringT);
		
	    // Step 1
		n = s.length();
		m = t.length();
		
		if(n == 0) {
			return m;
		}
		
		if(m == 0) {
			return n;
		}
		
		this.matrix = new int [n+1][m+1];

		// Step 2

		
		for(i = 0; i <= n; i++) {
			matrix[i][0] = i;
		}

		for(j = 0; j <= m; j++) {
			matrix[0][j] = j;
		}

		// Step 2.1 Initialize other values as -1, which will indicate that it's not yet finished
		for(i = 1; i <= n; i++) {
			for(j = 1; j <= m; j++) {
				matrix[i][j] = -1;
			}
		}
		
		
		// Step 3
		ArrayList<ComputeDistance> listComputeDistance = new ArrayList<ComputeDistance>();
		ComputeDistance computeDistance;
		
		for(i = 1; i <= n; i++) {
			for(j = 1; j <= m; j++) {
				computeDistance = new ComputeDistance(this, i, j);
				listComputeDistance.add(computeDistance);
			}
		}

		ExecutorService executor = Executors.newFixedThreadPool(n*m);
		try {
//			List<Future<Long>> results;
			executor.invokeAll(listComputeDistance);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		model.printMatrix(this.matrix);
		// Step 7
		return matrix[n][m];
	}


	public int[][] getMatrix() {
		return matrix;
	}


	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}


	public String getStringS() {
		return stringS;
	}


	public String getStringT() {
		return stringT;
	}


	public void setStringS(String stringS) {
		this.stringS = stringS;
	}


	public void setStringT(String stringT) {
		this.stringT = stringT;
	}


	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}
}
