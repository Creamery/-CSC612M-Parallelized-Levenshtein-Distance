package com.main;

import java.util.concurrent.Callable;

import com.model.ParallelAlgorithm;

public class ComputeDistance implements Callable<Long> {
	private ParallelAlgorithm algorithm;
	private int i;
	private int j;
	
	public ComputeDistance (ParallelAlgorithm algorithm, int i, int j) {
		this.algorithm = algorithm;
		this.i = i;
		this.j = j;
	}
	
	@Override
    public Long call() {
		long distance = 0;
		char s_i; // ith character of s
		char t_j; // jth character of t
		int cost; // cost
		
		// Wait while dependencies have not been computed
		while(algorithm.getMatrix()[i-1][j] == -1 ||
				algorithm.getMatrix()[i][j-1] == -1 ||
				algorithm.getMatrix()[i-1][j-1] == -1) {
			
		}
		
		t_j = algorithm.getStringT().charAt(j - 1);
		s_i = algorithm.getStringS().charAt(i - 1);
		
		// Step 5
		if(s_i == t_j) {
			cost = 0;
		}
		else {
			cost = 1;
		}
		// Step 6
		algorithm.getMatrix()[i][j] =
				algorithm.getModel().getMinimum(
						algorithm.getMatrix()[i-1][j]+1,
						algorithm.getMatrix()[i][j-1]+1,
						algorithm.getMatrix()[i-1][j-1] + cost);
		
    	return distance;
    }
}